package com.br.controle.gastos.dto;

import com.br.controle.gastos.model.GastosModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class GastosRequestDTO {
    private Long id;
    private String nome;
    private String descricao;
    private double valor;
    private String dataFim;
    private String dataInicio;
    private Long grupoGastosId;

    public GastosRequestDTO(GastosModel gastosModel) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.id = gastosModel.getId();
        this.nome = gastosModel.getNome();
        this.descricao = gastosModel.getDescricao();
        this.valor = gastosModel.getValor();
        this.grupoGastosId = gastosModel.getGrupoGastos().getId();
        this.dataInicio = (gastosModel.getDataInicio() != null) ? dateFormat.format(gastosModel.getDataInicio()) : null;
    }
}
