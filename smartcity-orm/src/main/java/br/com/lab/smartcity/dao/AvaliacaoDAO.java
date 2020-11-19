package br.com.lab.smartcity.dao;

import javax.persistence.EntityManager;

import br.com.lab.smartcity.domain.Avaliacao;
import br.com.lab.smartcity.domain.AvaliacaoId;
public class AvaliacaoDAO extends GenericDAO<Avaliacao, AvaliacaoId>{
	public AvaliacaoDAO(EntityManager em) {
		super(em);
	}
}