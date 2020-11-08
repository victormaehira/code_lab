package br.com.lab.smartcity.tests;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.smartcity.domain.Avaliacao;
import br.com.lab.smartcity.domain.AvaliacaoId;
import br.com.lab.smartcity.domain.Estabelecimento;
import br.com.lab.smartcity.domain.Usuario;

public class CreateAvaliacaoIdTest {
	public static void test() {
		System.out.println(">CreateAvaliacaoIdTest");
		EntityManager em = null;

		em = Persistence.createEntityManagerFactory("smartcity").createEntityManager();
		em.getTransaction().begin();
		
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setNome("Vou ser avaliado");
		
		em.persist(estabelecimento);
		em.flush();
		
		Usuario usuario = new Usuario();
		usuario.setNome("Vou fazer uma avaliacao");
		usuario.setSenha("112345678");
		usuario.setUsuario("teste");
		em.persist(usuario);
		em.flush();
		
		AvaliacaoId idNovo = new AvaliacaoId();
		idNovo.setEstabelecimento(estabelecimento.getId());
		idNovo.setUsuario(usuario.getId());
		
		Avaliacao novaAvaliacao = new Avaliacao();
		novaAvaliacao.setId(idNovo);
		//novaAvaliacao.setEstabelecimento(estabelecimento);
		//novaAvaliacao.setUsuario(usuario);
		novaAvaliacao.setNota(10);
		
		em.persist(novaAvaliacao);
		//em.persist(estabelecimento);
		//em.flush();
		em.getTransaction().commit();
		
		
		//Estabelecimento recuperado = em.find(Estabelecimento.class, 1);
		//Estabelecimento recuperado = em.find(Estabelecimento.class, novo.getId());
		//System.out.println(recuperado.getNome());
	}
}
