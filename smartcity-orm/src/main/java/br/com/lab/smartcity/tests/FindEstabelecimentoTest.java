package br.com.lab.smartcity.tests;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.smartcity.domain.Estabelecimento;

public class FindEstabelecimentoTest {
	public static void test() {
		System.out.println(">FindEstabelecimentoTest");
		EntityManager em = null;

		em = Persistence.createEntityManagerFactory("smartcity").createEntityManager();

		Estabelecimento recuperado = em.find(Estabelecimento.class, 1);
		System.out.println(recuperado.getNome());
	}
}
