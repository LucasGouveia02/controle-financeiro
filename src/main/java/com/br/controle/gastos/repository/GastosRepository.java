package com.br.controle.gastos.repository;

import com.br.controle.gastos.model.GastosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface GastosRepository extends JpaRepository<GastosModel, Long> {

    @Query("SELECT g FROM GastosModel g WHERE " +
            "(g.dataFim IS NULL AND g.dataInicio <= :dataConsulta) OR " +
            "(g.dataFim IS NOT NULL AND g.dataInicio <= :dataConsulta AND g.dataFim >= :dataConsulta) OR " +
            "(g.dataFim IS NOT NULL AND g.dataInicio <= :dataConsulta AND g.dataFim IS NULL) " +
            "AND g.dataInicio <= :dataConsulta")
    List<GastosModel> findGastosByMesAno(@Param("dataConsulta") Date dataConsulta);
}