package br.com.lab.smartcity.tests;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.smartcity.dao.TipoEstabelecimentoDAO;
import br.com.lab.smartcity.domain.TipoEstabelecimento;

public class GenericDAOTest {
	public static void test() {
		EntityManager em = null;
		try {
			em = Persistence.createEntityManagerFactory("smartcity").createEntityManager();
			TipoEstabelecimentoDAO dao = new TipoEstabelecimentoDAO(em);
			em.getTransaction().begin();

			TipoEstabelecimento novoTipoEstabelecimento = new TipoEstabelecimento();
			novoTipoEstabelecimento.setNome("Parque");
			dao.salvar(novoTipoEstabelecimento);

			em.merge(novoTipoEstabelecimento);
			
			TipoEstabelecimento entidade = dao.recuperar(novoTipoEstabelecimento.getId());
			if (entidade == null) {
				System.out.println("Não existe tipo de estabelecimento para a chave " + novoTipoEstabelecimento.getId());
			} else {
				System.out.println(" > " + entidade.getId() + " - " + entidade.getNome());
			}

			System.out.println("Tipos de Estabelecimento:");
			for (TipoEstabelecimento tipoEstabelecimento : dao.listar()) {
				System.out.println(" > " + tipoEstabelecimento.getId() + " - " + tipoEstabelecimento.getNome());
			}

			System.out.println("Excluindo...");
			dao.excluir(novoTipoEstabelecimento.getId());
			
			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			if (em != null) {
				em.close();
			}
			System.exit(0);
		}
	}
}
