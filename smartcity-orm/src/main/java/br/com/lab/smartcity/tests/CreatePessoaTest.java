package br.com.lab.smartcity.tests;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.smartcity.domain.Pessoa;

public class CreatePessoaTest {
	public static void test() {
		System.out.println(">CreatePessoaTest");
		
		EntityManager em = null;
		try {
			em = Persistence.createEntityManagerFactory("smartcity").createEntityManager();

			em.getTransaction().begin();
			//Pessoa pessoa = new Pessoa();
			Pessoa pessoa = new Pessoa(0, "Teste");
			
			em.persist(pessoa);
			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}
}
