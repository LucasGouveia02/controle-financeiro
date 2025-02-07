package com.br.controle.gastos.controller;

import com.br.controle.gastos.dto.GastosCompletoDTO;
import com.br.controle.gastos.dto.GastosDTO;
import com.br.controle.gastos.service.GastosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/gastos")
@CrossOrigin("*")
public class GastosController {

    @Autowired
    private GastosService gastosService;

    @PostMapping("/cadastrar")
    public ResponseEntity<GastosDTO> cadastrarGastos(@RequestBody GastosDTO gastosDTO) throws ParseException {
        return gastosService.cadastrarGastos(gastosDTO);
    }

    @GetMapping("/listar/{mesAno}")
    public ResponseEntity<List<GastosCompletoDTO>> listarGastos(@PathVariable String mesAno) throws ParseException {
        return gastosService.listarGastos(mesAno);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<GastosDTO> atualizarGastos(@RequestBody GastosDTO gastosDTO, @PathVariable Long id) throws ParseException {
        return gastosService.atualizarGastos(gastosDTO, id);
    }
}
