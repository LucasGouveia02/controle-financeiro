package com.br.controle.gastos.model;

import com.br.controle.gastos.dto.GastosRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
    private Date dataFim;
    private Date dataInicio;
    @ManyToOne
    @JoinColumn(name = "grupo_gastos_id")
    private GrupoGastosModel grupoGastos;

    public GastosModel(GastosRequestDTO gastosRequestDTO, GrupoGastosModel grupoGastos) {
        this.nome = gastosRequestDTO.getNome();
        this.descricao = gastosRequestDTO.getDescricao();
        this.valor = gastosRequestDTO.getValor();
        this.grupoGastos = grupoGastos;
    }
}
