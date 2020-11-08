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
			
			Estabelecimento queroSerAlterado = new Estabelecimento();
			queroSerAlterado.setNome("Vou ser alterado...");
			em.persist(queroSerAlterado);
			em.flush();

			Estabelecimento recuperado = em.find(Estabelecimento.class, queroSerAlterado.getId());
			recuperado.setNome("Fui alterado...");

			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}
}
