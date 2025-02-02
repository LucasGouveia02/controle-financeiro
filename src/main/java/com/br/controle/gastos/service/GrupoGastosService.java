package com.br.controle.gastos.service;

import com.br.controle.gastos.dto.GrupoGastosDTO;
import com.br.controle.gastos.model.GrupoGastosModel;
import com.br.controle.gastos.repository.GrupoGastosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GrupoGastosService {

    @Autowired
    private GrupoGastosRepository grupoGastosRepository;

    public ResponseEntity<GrupoGastosDTO> cadastrarGrupoGastos(GrupoGastosDTO dto) {
        GrupoGastosModel grupoGastosModel = grupoGastosRepository.findByNome(dto.getNome());
        if (grupoGastosModel != null) {
            throw new RuntimeException("Já existe um grupo de gastos com esse nome");
        }
        grupoGastosModel = new GrupoGastosModel(dto);
        GrupoGastosDTO response = new GrupoGastosDTO(grupoGastosRepository.save(grupoGastosModel));
        return ResponseEntity.ok(response);
    }
}
