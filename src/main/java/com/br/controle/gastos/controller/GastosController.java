package com.br.controle.gastos.controller;

import com.br.controle.gastos.dto.GastosDTO;
import com.br.controle.gastos.service.GastosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gastos")
public class GastosController {

    @Autowired
    private GastosService gastosService;

    @PostMapping("/cadastrar")
    public ResponseEntity<GastosDTO> cadastrarGastos(@RequestBody GastosDTO gastosDTO) {
        return gastosService.cadastrarGastos(gastosDTO);
    }
}
