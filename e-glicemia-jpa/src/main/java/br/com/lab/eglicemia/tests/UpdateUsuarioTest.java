package br.com.lab.eglicemia.tests;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.eglicemia.dao.UsuarioDAO;
import br.com.lab.eglicemia.domain.Usuario;

public class UpdateUsuarioTest {

		public static void test() {
		EntityManager em = null;
		try {
			em = Persistence.createEntityManagerFactory("eglicemia").createEntityManager();
			UsuarioDAO dao = new UsuarioDAO(em);
			em.getTransaction().begin();

			Usuario usuario = new Usuario();
			usuario.setNome("Edson Arante");
			usuario.setEmail("teste@teste.com");
			usuario.setPassword("secreto");			
			dao.salvar(usuario);
			
			//update
			usuario.setNome("Maradona");
			dao.salvar(usuario);
			
			em.getTransaction().commit();

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
