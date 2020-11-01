package br.com.lab.smartcity.tests;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.smartcity.domain.Estabelecimento;

public class CreateEstabelecimentoTest {
	public static void test() {
		System.out.println(">CreateEstabelecimentoTest");
		EntityManager em = null;
		try {
			em = Persistence.createEntityManagerFactory("smartcity").createEntityManager();

			em.getTransaction().begin();

			Estabelecimento novo = new Estabelecimento();
			novo.setNome("Escolinha Imaginação");

			em.persist(novo);

			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}
}
