package br.com.lab.eglicemia.tests;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.eglicemia.dao.GlicemiaDAO;
import br.com.lab.eglicemia.domain.Glicemia;

public class DeleteGlicemiaPorDataTest {
	public static void test() {
		EntityManager em = null;
		try {
			em = Persistence.createEntityManagerFactory("eglicemia").createEntityManager();
			em.getTransaction().begin();
			
			GlicemiaDAO glicemiaDao = new GlicemiaDAO(em);
			Glicemia glicemia = new Glicemia();
			glicemia.setData(new GregorianCalendar(2020, Calendar.NOVEMBER, 22));

			Glicemia glicemiaAntiga = new Glicemia();
			glicemiaAntiga.setData(new GregorianCalendar(2018, Calendar.JULY, 5));
			
			Glicemia glicemiaNova = new Glicemia();
			glicemiaNova.setValor(220.0f);
			glicemiaNova.setData(new GregorianCalendar(2021, Calendar.FEBRUARY, 6));

			
			glicemiaDao.salvar(glicemia);
			glicemiaDao.salvar(glicemiaAntiga);
			glicemiaDao.salvar(glicemiaNova);
			
			glicemiaDao.excluirAntesDe(new GregorianCalendar(2021, Calendar.JANUARY, 1));
			
			em.getTransaction().commit();
		
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			for(Glicemia glico: glicemiaDao.listar()) {
				Date date = glico.getData().getTime();
				System.out.println("Nao fui cortada: " + format.format(date));
			}

		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
}
