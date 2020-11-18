package br.com.lab.smartcity.dao;

import javax.persistence.EntityManager;

import br.com.lab.smartcity.domain.TipoEstabelecimento;
public class TipoEstabelecimentoDAO extends GenericDAO<TipoEstabelecimento, Integer> {
	public TipoEstabelecimentoDAO(EntityManager em) {
		super(em);
	}
}