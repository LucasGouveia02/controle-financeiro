package com.br.controle.gastos.repository;

import com.br.controle.gastos.model.GrupoGastosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoGastosRepository extends JpaRepository<GrupoGastosModel, Long> {
    GrupoGastosModel findByNome(String nome);
}
