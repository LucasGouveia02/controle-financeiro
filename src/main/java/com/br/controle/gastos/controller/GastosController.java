package com.br.controle.gastos.controller;

import com.br.controle.gastos.dto.GastosCompletoDTO;
import com.br.controle.gastos.dto.GastosDTO;
import com.br.controle.gastos.service.GastosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gastos")
@CrossOrigin("*")
public class GastosController {

    @Autowired
    private GastosService gastosService;

    @PostMapping("/cadastrar")
    public ResponseEntity<GastosDTO> cadastrarGastos(@RequestBody GastosDTO gastosDTO) {
        return gastosService.cadastrarGastos(gastosDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<GastosCompletoDTO>> listarGastos() {
        return gastosService.listarGastos();
    }
}
