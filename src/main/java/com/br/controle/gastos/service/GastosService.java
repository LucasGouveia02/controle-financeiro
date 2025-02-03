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

import java.util.ArrayList;
import java.util.List;

@Service
public class GastosService {

    @Autowired
    private GastosRepository gastosRepository;

    @Autowired
    private GrupoGastosRepository grupoGastosRepository;

    public ResponseEntity<GastosDTO> cadastrarGastos(GastosDTO gastosDTO) {
        GrupoGastosModel grupoGastosModel = grupoGastosRepository.getReferenceById(gastosDTO.getGrupoGastosId());
        GastosModel gastosModel = new GastosModel(gastosDTO, grupoGastosModel);
        GastosDTO response = new GastosDTO(gastosRepository.save(gastosModel));
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<List<GastosCompletoDTO>> listarGastos() {
        List<GastosModel> list = gastosRepository.findAll();
        List<GastosCompletoDTO> listCompleta = new ArrayList<>();
        for (GastosModel gasto : list) {
            listCompleta.add(new GastosCompletoDTO(gasto, gasto.getGrupoGastos()));
        }
        return ResponseEntity.ok(listCompleta);
    }
}
