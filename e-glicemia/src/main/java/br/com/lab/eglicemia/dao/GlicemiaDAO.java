package br.com.lab.eglicemia.dao;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.lab.eglicemia.domain.Glicemia;

public class GlicemiaDAO extends GenericDAO<Glicemia, Integer>{

	public GlicemiaDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}
	
	public void excluirAntesDe(Calendar data) {
		this.em.createQuery("delete from Glicemia g where g.data < :data").setParameter("data", data)
				.executeUpdate();
	}

	public int excluirAntesDeQtd(Calendar data) {
		return this.em.createQuery("delete from Glicemia g where g.data < :data")
				.setParameter("data", data).executeUpdate();
	}


}
