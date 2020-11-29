package com.lab.victor.api.repository.chave;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lab.victor.api.model.Chave;
import com.lab.victor.api.repository.filter.ChaveFilter;
import com.lab.victor.api.repository.projection.ResumoChave;

public interface ChaveRepositoryQuery {
	public Page<Chave> filtrar(ChaveFilter chaveFilter, Pageable pageable);
	public Page<ResumoChave> resumir(ChaveFilter chaveFilter, Pageable pageable);
}
