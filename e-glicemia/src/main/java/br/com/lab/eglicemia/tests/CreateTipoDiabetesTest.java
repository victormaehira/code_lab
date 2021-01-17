package br.com.lab.eglicemia.tests;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.eglicemia.dao.TipoDiabetesDAO;
import br.com.lab.eglicemia.domain.TipoDiabetes;

public class CreateTipoDiabetesTest {
	public static void test() {
		EntityManager em = null;
		try {
			em = Persistence.createEntityManagerFactory("eglicemia").createEntityManager();
			em.getTransaction().begin();

			TipoDiabetesDAO tipoDiabetesDAO = new TipoDiabetesDAO(em);
			TipoDiabetes tipoDiabetes = new TipoDiabetes();
			tipoDiabetes.setTipo("GESTACIONAL");
			tipoDiabetesDAO.salvar(tipoDiabetes);
		
			em.getTransaction().commit();

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
