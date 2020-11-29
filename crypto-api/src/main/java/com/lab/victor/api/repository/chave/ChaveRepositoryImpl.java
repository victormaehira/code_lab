package com.lab.victor.api.repository.chave;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.lab.victor.api.model.Categoria_;
import com.lab.victor.api.model.Chave;
import com.lab.victor.api.model.Chave_;
import com.lab.victor.api.model.Pessoa_;
import com.lab.victor.api.repository.filter.ChaveFilter;
import com.lab.victor.api.repository.projection.ResumoChave;

public class ChaveRepositoryImpl  implements ChaveRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Chave> filtrar(ChaveFilter chaveFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Chave> criteria = builder.createQuery(Chave.class);
		Root<Chave> root = criteria.from(Chave.class);
		Predicate[] predicates = criarRestricoes(chaveFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Chave> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(chaveFilter));
	}
	
	@Override
	public Page<ResumoChave> resumir(ChaveFilter chaveFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoChave> criteria = builder.createQuery(ResumoChave.class);
		Root<Chave> root = criteria.from(Chave.class);
		
		criteria.select(builder.construct(ResumoChave.class
				, root.get(Chave_.codigo), root.get(Chave_.descricao)
				, root.get(Chave_.dataVencimento), root.get(Chave_.dataPagamento)
				, root.get(Chave_.valor), root.get(Chave_.tipo)
				, root.get(Chave_.categoria).get(Categoria_.nome)
				, root.get(Chave_.pessoa).get(Pessoa_.nome)));
		
		Predicate[] predicates = criarRestricoes(chaveFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ResumoChave> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(chaveFilter));
	}

	private Predicate[] criarRestricoes(ChaveFilter ChaveFilter, CriteriaBuilder builder,
			Root<Chave> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(ChaveFilter.getDescricao())) {
			predicates.add(builder.like(
					builder.lower(root.get(Chave_.descricao)), "%" + ChaveFilter.getDescricao().toLowerCase() + "%"));
		}
		
		if (ChaveFilter.getDataVencimentoDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Chave_.dataVencimento), ChaveFilter.getDataVencimentoDe()));
		}
		
		if (ChaveFilter.getDataVencimentoAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Chave_.dataVencimento), ChaveFilter.getDataVencimentoAte()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(ChaveFilter chaveFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Chave> root = criteria.from(Chave.class);
		
		Predicate[] predicates = criarRestricoes(chaveFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}