package com.br.controle.gastos.controller;

import com.br.controle.gastos.dto.GrupoGastosDTO;
import com.br.controle.gastos.service.GrupoGastosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grupo-gastos")
@CrossOrigin("*")
public class GrupoGastosController {

    @Autowired
    private GrupoGastosService grupoGastosService;

    @PostMapping("/cadastrar")
    public ResponseEntity<GrupoGastosDTO> cadastrarGrupoGastos(@RequestBody GrupoGastosDTO dto) {
        return grupoGastosService.cadastrarGrupoGastos(dto);
    }
}
