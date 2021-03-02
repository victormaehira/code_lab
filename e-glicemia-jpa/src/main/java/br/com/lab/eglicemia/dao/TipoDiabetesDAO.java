package br.com.lab.eglicemia.dao;

import javax.persistence.EntityManager;

import br.com.lab.eglicemia.domain.TipoDiabetes;

public class TipoDiabetesDAO extends GenericDAO<TipoDiabetes, Integer>{

	public TipoDiabetesDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

}