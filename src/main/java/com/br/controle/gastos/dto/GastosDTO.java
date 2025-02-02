package com.br.controle.gastos.dto;

import com.br.controle.gastos.model.GastosModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class GastosDTO {
    private String nome;
    private String descricao;
    private double valor;
    private Long grupoGastosId;

    public GastosDTO(GastosModel gastosModel) {
        this.nome = gastosModel.getNome();
        this.descricao = gastosModel.getDescricao();
        this.valor = gastosModel.getValor();
        this.grupoGastosId = gastosModel.getGrupoGastos().getId();
    }
}
