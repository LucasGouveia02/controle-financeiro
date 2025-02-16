package com.br.controle.gastos.dto;

import com.br.controle.gastos.model.GastosModel;
import com.br.controle.gastos.model.GrupoGastosModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.br.controle.gastos.service.GastosService.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GastosResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private double valor;
    private String parcela;
    private String dataInicio;
    private String dataFim;
    private GrupoGastosDTO grupoGastos;

    public GastosResponseDTO(GastosModel gastosModel, GrupoGastosModel grupoGastosModel, String parcela) {
        this.id = gastosModel.getId();
        this.nome = gastosModel.getNome();
        this.descricao = gastosModel.getDescricao();
        this.valor = gastosModel.getValor();
        this.parcela = parcela;
        this.dataInicio = returnDataInicio(gastosModel);
        this.dataFim = returnDataFim(gastosModel);
        this.grupoGastos = new GrupoGastosDTO(grupoGastosModel);
    }
}
