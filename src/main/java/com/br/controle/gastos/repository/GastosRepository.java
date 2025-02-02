package com.br.controle.gastos.repository;

import com.br.controle.gastos.model.GastosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GastosRepository extends JpaRepository<GastosModel, Long> {
}
