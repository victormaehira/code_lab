package br.com.lab.smartcity.tests;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.smartcity.domain.Estabelecimento;

public class FindEstabelecimentoTest {
	public static void test() {
		System.out.println(">FindEstabelecimentoTest");
		EntityManager em = null;

		em = Persistence.createEntityManagerFactory("smartcity").createEntityManager();
		em.getTransaction().begin();
		
		Estabelecimento novo = new Estabelecimento();
		novo.setNome("Vou ser recuperado");
		em.persist(novo);
		em.flush();
		em.getTransaction().commit();
		
		
		//Estabelecimento recuperado = em.find(Estabelecimento.class, 1);
		Estabelecimento recuperado = em.find(Estabelecimento.class, novo.getId());
		System.out.println(recuperado.getNome());
	}
}
