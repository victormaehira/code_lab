package br.com.lab.eglicemia.tests;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.eglicemia.dao.UsuarioDAO;
import br.com.lab.eglicemia.domain.Usuario;

public class ReadUsuarioByNameTest {
	public static void test() {
		EntityManager em = null;
		try {
			em = Persistence.createEntityManagerFactory("eglicemia").createEntityManager();
			UsuarioDAO dao = new UsuarioDAO(em);
			em.getTransaction().begin();

			Usuario usuario = new Usuario();
			usuario.setNome("Edson Arante");
			
			Usuario usuario2 = new Usuario();
			usuario2.setNome("Diego Armando");
			
			Usuario usuario3 = new Usuario();
			usuario3.setNome("Silva");
			
			dao.salvar(usuario);
			dao.salvar(usuario2);
			dao.salvar(usuario3);
		
			em.getTransaction().commit();
			
			System.out.println("Listar usuarios com nome Silva");
			for(Usuario user: dao.listarPorNome("Silva")) {
				System.out.println("Meu nome é " + user.getNome());
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
