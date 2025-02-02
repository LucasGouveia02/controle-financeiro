package com.br.controle.gastos.model;

import com.br.controle.gastos.dto.GrupoGastosDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grupo_gastos")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class GrupoGastosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "grupoGastos", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GastosModel> gastos = new ArrayList<>();

    public GrupoGastosModel(GrupoGastosDTO dto) {
        this.nome = dto.getNome();
    }
}