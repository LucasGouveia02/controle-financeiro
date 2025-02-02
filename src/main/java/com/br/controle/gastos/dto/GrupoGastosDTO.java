package com.br.controle.gastos.dto;

import com.br.controle.gastos.model.GrupoGastosModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class GrupoGastosDTO {
    private Long id;
    private String nome;

    public GrupoGastosDTO(GrupoGastosModel gastosModel) {
        this.id = gastosModel.getId();
        this.nome = gastosModel.getNome();
    }
}
