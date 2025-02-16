package com.br.controle.gastos.service;

import com.br.controle.gastos.dto.GastosResponseDTO;
import com.br.controle.gastos.dto.GastosRequestDTO;
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

    public ResponseEntity<GastosRequestDTO> cadastrarGastos(GastosRequestDTO gastosRequestDTO) throws ParseException {
        GrupoGastosModel grupoGastosModel = grupoGastosRepository.getReferenceById(gastosRequestDTO.getGrupoGastosId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date dataInicio = null;
        Date dataFim = null;
        if (gastosRequestDTO.getDataInicio() != null) {
            dataInicio = dateFormat.parse(gastosRequestDTO.getDataInicio());
        }
        if (gastosRequestDTO.getDataFim() != null) {
            dataFim = dateFormat.parse(gastosRequestDTO.getDataFim());
        }
        GastosModel gastosModel = new GastosModel(gastosRequestDTO, grupoGastosModel);
        gastosModel.setDataFim(dataFim);
        gastosModel.setDataInicio(dataInicio);
        GastosRequestDTO response = new GastosRequestDTO(gastosRepository.save(gastosModel));
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<List<GastosResponseDTO>> listarGastos(String mesAno) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
        Date dataConsulta = dateFormat.parse(mesAno);
        List<GastosModel> list = gastosRepository.findGastosByMesAno(dataConsulta);
        List<GastosResponseDTO> listCompleta = new ArrayList<>();
        for (GastosModel gasto : list) {
            String parcela = calcularParcela(gasto.getDataInicio(), gasto.getDataFim(), dataConsulta);
            listCompleta.add(new GastosResponseDTO(gasto, gasto.getGrupoGastos(), parcela));
        }
        return ResponseEntity.ok(listCompleta);

    }

    public ResponseEntity<GastosRequestDTO> atualizarGastos(GastosRequestDTO gastosRequestDTO, Long id) throws ParseException {
        GastosModel gastosModel = gastosRepository.getReferenceById(id);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date dataInicio = null;
        Date dataFim = null;
        if (gastosRequestDTO.getDataInicio() != null) {
            dataInicio = dateFormat.parse(gastosRequestDTO.getDataInicio());
        }
        if (gastosRequestDTO.getDataFim() != null) {
            dataFim = dateFormat.parse(gastosRequestDTO.getDataFim());
        }
        gastosModel.setNome(gastosRequestDTO.getNome());
        gastosModel.setDescricao(gastosRequestDTO.getDescricao());
        gastosModel.setValor(gastosRequestDTO.getValor());
        gastosModel.setGrupoGastos(
                grupoGastosRepository.findById(gastosRequestDTO.getGrupoGastosId()).
                        orElseThrow(
                                () -> new RuntimeException("Grupo de gastos não encontrado")
                        )
        );
        gastosModel.setDataFim(dataFim);
        gastosModel.setDataInicio(dataInicio);
        GastosRequestDTO response = new GastosRequestDTO(gastosRepository.save(gastosModel));
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<GastosResponseDTO> listarGastosPorId(Long id) {
        GastosModel gastosModel = gastosRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Gastos não encontrado")
        );
        GrupoGastosModel grupoGastosModel = grupoGastosRepository.findById(gastosModel.getGrupoGastos().getId()).orElseThrow(
                () -> new RuntimeException("Grupo de gastos não encontrado")
        );
        GastosResponseDTO response = new GastosResponseDTO(gastosModel, grupoGastosModel, null);
        return ResponseEntity.ok(response);
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

    public static String returnDataFim(GastosModel gastosModel) {
        String retorno = "";
        if (gastosModel.getDataFim() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
            retorno = dateFormat.format(gastosModel.getDataFim());
        }
        return retorno;
    }

    public String calcularParcela(Date dataInicio, Date dataFim, Date dataConsulta) {
        if (dataInicio == null || dataFim == null || dataConsulta == null) {
            return "0/0";
        }

        LocalDate inicio = convertToLocalDate(dataInicio);
        LocalDate fim = convertToLocalDate(dataFim);
        LocalDate consulta = convertToLocalDate(dataConsulta);

        long totalMeses = ChronoUnit.MONTHS.between(inicio, fim) + 1;
        long mesesPassados = ChronoUnit.MONTHS.between(inicio, consulta) + 1;

        return mesesPassados + "/" + totalMeses;
    }
}
