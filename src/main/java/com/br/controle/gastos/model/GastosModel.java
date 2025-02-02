package com.br.controle.gastos.model;

import com.br.controle.gastos.dto.GastosDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gastos")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class GastosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private double valor;
    @ManyToOne
    @JoinColumn(name = "grupo_gastos_id")
    private GrupoGastosModel grupoGastos;

    public GastosModel(GastosDTO gastosDTO, GrupoGastosModel grupoGastos) {
        this.nome = gastosDTO.getNome();
        this.descricao = gastosDTO.getDescricao();
        this.valor = gastosDTO.getValor();
        this.grupoGastos = grupoGastos;
    }
}
