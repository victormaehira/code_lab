package br.com.lab.smartcity.tests;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class CreateEntityManagerTest {
	public static void test() {
		System.out.println(">CreateEntityManagerTest");
		EntityManager em = null;
		try {
			em = Persistence.createEntityManagerFactory("smartcity").createEntityManager();

		} catch (Exception e) {

			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();

		}

		if (em != null) {
			em.close();
		}
		//System.exit(0);
	}
}
