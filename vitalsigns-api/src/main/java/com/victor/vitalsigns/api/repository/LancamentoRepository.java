package com.victor.vitalsigns.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.victor.vitalsigns.api.model.Lancamento;
import com.victor.vitalsigns.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
