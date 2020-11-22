package br.com.lab.smartcity.tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.smartcity.dao.TipoEstabelecimentoDAO;
import br.com.lab.smartcity.domain.TipoEstabelecimento;

public class TipoEstabelecimentoDaoListOrdenadoPorNomeTest {
	public static void test() {
		EntityManager em = Persistence.createEntityManagerFactory("smartcity").createEntityManager();
		em.getTransaction().begin();
		TipoEstabelecimentoDAO dao = new TipoEstabelecimentoDAO(em);
		
		TipoEstabelecimento tipo = new TipoEstabelecimento();
		tipo.setNome("Pet shop");
		TipoEstabelecimento mercado = new TipoEstabelecimento();
		mercado.setNome("Mercado");
		TipoEstabelecimento sorveteria = new TipoEstabelecimento();
		sorveteria.setNome("sorveteria");
		TipoEstabelecimento escola = new TipoEstabelecimento();
		escola.setNome("escola");
		
		dao.salvar(tipo);
		dao.salvar(mercado);
		dao.salvar(sorveteria);
		dao.salvar(escola);
		
		List<TipoEstabelecimento> lista = dao.listarOrdenadoNome();
		System.out.println("Teste listar ordenado por nome TipoEstabelecimentoDAO");
		for (TipoEstabelecimento tipoEstabelecimento : lista) {
			System.out.println(">" + tipoEstabelecimento.getNome());
		}
		
		em.getTransaction().commit();
		em.close();
	}
}
