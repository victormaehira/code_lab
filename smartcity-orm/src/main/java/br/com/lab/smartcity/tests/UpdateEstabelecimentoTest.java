package br.com.lab.smartcity.tests;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.smartcity.domain.Estabelecimento;

public class UpdateEstabelecimentoTest {
	public static void test() {
		System.out.println(">UpdateEstabelecimentoTest");
		EntityManager em = null;
		try {
			em = Persistence.createEntityManagerFactory("smartcity").createEntityManager();

			em.getTransaction().begin();

			Estabelecimento recuperado = em.find(Estabelecimento.class, 1);
			recuperado.setNome("Escola Magic");

			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}
}
