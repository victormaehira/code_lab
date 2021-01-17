package br.com.lab.eglicemia.tests;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.eglicemia.dao.GlicemiaDAO;
import br.com.lab.eglicemia.domain.Glicemia;

public class DeleteGlicemiaDoAnoPassado {
	private static final int ONZE_HORAS = 11;
	private static final int TRINTA_MINUTOS = 30;
	private static final int DEZENOVE_HORAS = 19;
	private static final int VINTE_HORAS = 20;
	public static void test() {
		EntityManager em = null;
		try {
			em = Persistence.createEntityManagerFactory("eglicemia").createEntityManager();
			em.getTransaction().begin();
			
			System.out.println("Criando Resultados de Glicemia.");
			GlicemiaDAO glicemiaDao = new GlicemiaDAO(em);
			Glicemia glicemiaAntesDoAlmocoAntiga = new Glicemia(220f, new GregorianCalendar(2020, Calendar.JANUARY, Calendar.DAY_OF_MONTH, ONZE_HORAS, TRINTA_MINUTOS)); 
			Glicemia glicemiaAntesDaJantaAntiga = new Glicemia(130f, new GregorianCalendar(2020, Calendar.JANUARY, Calendar.DAY_OF_MONTH, DEZENOVE_HORAS, TRINTA_MINUTOS));
			Glicemia glicemiaAntesDaJantaRecente = new Glicemia(130f, new GregorianCalendar(2021, Calendar.JANUARY, Calendar.DAY_OF_MONTH, VINTE_HORAS, TRINTA_MINUTOS));
			glicemiaDao.salvar(glicemiaAntesDoAlmocoAntiga);
			glicemiaDao.salvar(glicemiaAntesDaJantaAntiga);
			glicemiaDao.salvar(glicemiaAntesDaJantaRecente);
			
			System.out.println("Excluindo glicemias do ano passado.");
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
