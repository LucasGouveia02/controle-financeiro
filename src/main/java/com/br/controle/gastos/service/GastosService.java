package com.br.controle.gastos.service;

import com.br.controle.gastos.dto.GastosCompletoDTO;
import com.br.controle.gastos.dto.GastosDTO;
import com.br.controle.gastos.model.GastosModel;
import com.br.controle.gastos.model.GrupoGastosModel;
import com.br.controle.gastos.repository.GastosRepository;
import com.br.controle.gastos.repository.GrupoGastosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GastosService {

    @Autowired
    private GastosRepository gastosRepository;

    @Autowired
    private GrupoGastosRepository grupoGastosRepository;

    public ResponseEntity<GastosDTO> cadastrarGastos(GastosDTO gastosDTO) throws ParseException {
        GrupoGastosModel grupoGastosModel = grupoGastosRepository.getReferenceById(gastosDTO.getGrupoGastosId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date dataInicio = dateFormat.parse(gastosDTO.getDataInicio());
        Date dataFim = new Date();
        if (gastosDTO.getParcela() != null) {
            String[] parcelaParts = gastosDTO.getParcela().split("/");
            int mesesRestantes = Integer.parseInt(parcelaParts[1]) - Integer.parseInt(parcelaParts[0]);
            dataFim.setMonth(dataFim.getMonth() + mesesRestantes);
        } else {
            dataFim = null;
        }
        GastosModel gastosModel = new GastosModel(gastosDTO, grupoGastosModel);
        gastosModel.setDataFim(dataFim);
        gastosModel.setDataInicio(dataInicio);
        GastosDTO response = new GastosDTO(gastosRepository.save(gastosModel));
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<List<GastosCompletoDTO>> listarGastos(String mesAno) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
        Date dataConsulta = dateFormat.parse(mesAno);
        List<GastosModel> list = gastosRepository.findGastosByMesAno(dataConsulta);
        List<GastosCompletoDTO> listCompleta = new ArrayList<>();
        for (GastosModel gasto : list) {
            listCompleta.add(new GastosCompletoDTO(gasto, gasto.getGrupoGastos()));
        }
        return ResponseEntity.ok(listCompleta);

    }

    public ResponseEntity<GastosDTO> atualizarGastos(GastosDTO gastosDTO, Long id) throws ParseException {
        GastosModel gastosModel = gastosRepository.getReferenceById(id);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date dataInicio = dateFormat.parse(gastosDTO.getDataInicio());
        Date dataFim = new Date();
        if (gastosDTO.getParcela() != null) {
            String[] parcelaParts = gastosDTO.getParcela().split("/");
            int mesesRestantes = Integer.parseInt(parcelaParts[1]) - Integer.parseInt(parcelaParts[0]);
            dataFim.setMonth(dataFim.getMonth() + mesesRestantes);
        } else {
            dataFim = null;
        }
        gastosModel.setNome(gastosDTO.getNome());
        gastosModel.setDescricao(gastosDTO.getDescricao());
        gastosModel.setValor(gastosDTO.getValor());
        gastosModel.setGrupoGastos(
                grupoGastosRepository.findById(gastosDTO.getGrupoGastosId()).
                        orElseThrow(
                                () -> new RuntimeException("Grupo de gastos não encontrado")
                        )
        );
        gastosModel.setDataFim(dataFim);
        gastosModel.setDataInicio(dataInicio);
        GastosDTO response = new GastosDTO(gastosRepository.save(gastosModel));
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<GastosCompletoDTO> listarGastosPorId(Long id) {
        GastosModel gastosModel = gastosRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Gastos não encontrado")
        );
        GrupoGastosModel grupoGastosModel = grupoGastosRepository.findById(gastosModel.getGrupoGastos().getId()).orElseThrow(
                () -> new RuntimeException("Grupo de gastos não encontrado")
        );
        GastosCompletoDTO response = new GastosCompletoDTO(gastosModel, grupoGastosModel);
        return ResponseEntity.ok(response);
    }

    public static long monthsBetween(Date startDate, Date endDate) {
        long retorno = 0;
        if (startDate != null && endDate != null) {
            retorno = ChronoUnit.MONTHS.between(convertToLocalDate(startDate), convertToLocalDate(endDate));
        }
        return retorno;
    }

    private static LocalDate convertToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static String returnDataInicio(GastosModel gastosModel) {
        String retorno = "";
        if (gastosModel.getDataInicio() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
            retorno = dateFormat.format(gastosModel.getDataInicio());
        }
        return retorno;
    }
}
