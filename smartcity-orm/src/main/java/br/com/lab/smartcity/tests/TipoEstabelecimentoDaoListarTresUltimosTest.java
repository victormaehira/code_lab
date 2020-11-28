package br.com.lab.smartcity.tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.smartcity.dao.TipoEstabelecimentoDAO;
import br.com.lab.smartcity.domain.TipoEstabelecimento;

public class TipoEstabelecimentoDaoListarTresUltimosTest {
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
		TipoEstabelecimento padaria = new TipoEstabelecimento();
		padaria.setNome("padaria");
		
		dao.salvar(tipo);
		dao.salvar(mercado);
		dao.salvar(sorveteria);
		dao.salvar(escola);
		dao.salvar(padaria);
		
		List<TipoEstabelecimento> lista = dao.listarTresUltimos();
		System.out.println("Teste listar 3 ultimos por nome TipoEstabelecimentoDAO");
		for (TipoEstabelecimento tipoEstabelecimento : lista) {
			System.out.println(">" + tipoEstabelecimento.getNome());
		}
		
		em.getTransaction().commit();
		em.close();
	}
}
