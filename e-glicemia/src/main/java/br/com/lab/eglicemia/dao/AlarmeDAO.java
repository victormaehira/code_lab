package br.com.lab.eglicemia.dao;

import javax.persistence.EntityManager;

import br.com.lab.eglicemia.domain.Alarme;

public class AlarmeDAO extends GenericDAO<Alarme, Integer>{

	public AlarmeDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

}
