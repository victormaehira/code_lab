package br.com.lab.smartcity.tests;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.smartcity.domain.Estabelecimento;

public class DeleteEstabelecimentoTest {
	public static void test() {
		System.out.println(">DeleteEstabelecimentoTest");
		EntityManager em = null;
		try {
			em = Persistence.createEntityManagerFactory("smartcity").createEntityManager();

			em.getTransaction().begin();

			Estabelecimento queroSerExcluido = new Estabelecimento();
			queroSerExcluido.setNome("Fui...");
			em.persist(queroSerExcluido);
			em.flush();
			
			//Estabelecimento recuperado = em.find(Estabelecimento.class, 1);
			em.remove(queroSerExcluido);
			
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}
}
