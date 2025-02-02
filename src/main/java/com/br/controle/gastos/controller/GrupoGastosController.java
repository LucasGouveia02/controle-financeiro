package com.br.controle.gastos.controller;

import com.br.controle.gastos.dto.GrupoGastosDTO;
import com.br.controle.gastos.service.GrupoGastosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grupo-gastos")
public class GrupoGastosController {

    @Autowired
    private GrupoGastosService grupoGastosService;

    @PostMapping("/cadastrar")
    public ResponseEntity<GrupoGastosDTO> cadastrarGrupoGastos(@RequestBody GrupoGastosDTO dto) {
        return grupoGastosService.cadastrarGrupoGastos(dto);
    }
}
