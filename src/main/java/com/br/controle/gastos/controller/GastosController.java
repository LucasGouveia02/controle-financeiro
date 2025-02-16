package com.br.controle.gastos.controller;

import com.br.controle.gastos.dto.GastosResponseDTO;
import com.br.controle.gastos.dto.GastosRequestDTO;
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
    public ResponseEntity<GastosRequestDTO> cadastrarGastos(@RequestBody GastosRequestDTO gastosRequestDTO) throws ParseException {
        return gastosService.cadastrarGastos(gastosRequestDTO);
    }

    @GetMapping("/listar/{mesAno}")
    public ResponseEntity<List<GastosResponseDTO>> listarGastos(@PathVariable String mesAno) throws ParseException {
        return gastosService.listarGastos(mesAno);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<GastosRequestDTO> atualizarGastos(@RequestBody GastosRequestDTO gastosRequestDTO, @PathVariable Long id) throws ParseException {
        return gastosService.atualizarGastos(gastosRequestDTO, id);
    }

    @GetMapping("/listarPorId/{id}")
    public ResponseEntity<GastosResponseDTO> listarGastosPorId(@PathVariable Long id) {
        return gastosService.listarGastosPorId(id);
    }
}
