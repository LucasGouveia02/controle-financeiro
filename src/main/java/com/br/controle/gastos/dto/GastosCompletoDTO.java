package com.br.controle.gastos.dto;

import com.br.controle.gastos.model.GastosModel;
import com.br.controle.gastos.model.GrupoGastosModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.br.controle.gastos.service.GastosService.monthsBetween;
import static com.br.controle.gastos.service.GastosService.returnDataInicio;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GastosCompletoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private double valor;
    private String parcela;
    private String dataInicio;
    private GrupoGastosDTO grupoGastos;

    public GastosCompletoDTO(GastosModel gastosModel, GrupoGastosModel grupoGastosModel) {
        this.id = gastosModel.getId();
        this.nome = gastosModel.getNome();
        this.descricao = gastosModel.getDescricao();
        this.valor = gastosModel.getValor();
        this.parcela = Long.toString(monthsBetween(gastosModel.getDataInicio(), gastosModel.getDataFim()));
        this.dataInicio = returnDataInicio(gastosModel);
        this.grupoGastos = new GrupoGastosDTO(grupoGastosModel);
    }
}
