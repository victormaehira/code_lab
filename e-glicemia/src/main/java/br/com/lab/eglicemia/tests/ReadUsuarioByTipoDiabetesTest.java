package br.com.lab.eglicemia.tests;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.eglicemia.dao.UsuarioDAO;
import br.com.lab.eglicemia.domain.TipoDiabetes;
import br.com.lab.eglicemia.domain.Usuario;

public class ReadUsuarioByTipoDiabetesTest {
	public static void test() {
		EntityManager em = null;
		try {
			em = Persistence.createEntityManagerFactory("eglicemia").createEntityManager();
			UsuarioDAO dao = new UsuarioDAO(em);
			em.getTransaction().begin();

			TipoDiabetes diabetesGestacional = new TipoDiabetes("GESTACIONAL");
			TipoDiabetes diabetesTipo1 = new TipoDiabetes("TIPO_1");

			System.out.println("Inserindo usuarios");
			Usuario usuarioComDiabetesTipo1 = new Usuario("Edson Arantes", "pele@fiap.com.br", "secreto", diabetesTipo1, null, null);
			Usuario usuarioComDiabetesGestacional = new Usuario("Mariana Silva", "mary@teste.com", "senha", diabetesGestacional, null, null);
			
			dao.salvar(usuarioComDiabetesTipo1);
			dao.salvar(usuarioComDiabetesGestacional);
			em.getTransaction().commit();
			
			System.out.println("Buscando apenas usuarios com um tipo de diabetes especifica:");
			for(Usuario user: dao.listarPorTipoDiabetes(diabetesGestacional)) {
				System.out.println("Eu tenho diabetes gestacional: " + user.getNome());
			}

		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
}
