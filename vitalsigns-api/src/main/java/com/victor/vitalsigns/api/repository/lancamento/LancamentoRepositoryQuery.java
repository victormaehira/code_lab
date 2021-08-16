package com.victor.vitalsigns.api.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.victor.vitalsigns.api.model.Lancamento;
import com.victor.vitalsigns.api.repository.filter.LancamentoFilter;
import com.victor.vitalsigns.api.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {

	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
	
}
