package br.com.lab.eglicemia.tests;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.eglicemia.dao.TipoDiabetesDAO;
import br.com.lab.eglicemia.dao.UsuarioDAO;
import br.com.lab.eglicemia.domain.TipoDiabetes;
import br.com.lab.eglicemia.domain.Usuario;

public class DeleteUsuarioTest {
	public static void test() {
		EntityManager em = null;
		try {
			em = Persistence.createEntityManagerFactory("eglicemia").createEntityManager();
			UsuarioDAO dao = new UsuarioDAO(em);
			em.getTransaction().begin();

			TipoDiabetesDAO tipoDiabetesDAO = new TipoDiabetesDAO(em);
			TipoDiabetes tipoDiabetes = new TipoDiabetes();
			tipoDiabetes.setTipo("GESTACIONAL");
			tipoDiabetesDAO.salvar(tipoDiabetes);
			em.merge(tipoDiabetes);
			

			Usuario usuario = new Usuario();
			usuario.setNome("Edson Arante");
			usuario.setEmail("teste@teste.com");
			usuario.setPassword("secreto");
			usuario.setTipoDiabetes(tipoDiabetes);
			
			dao.salvar(usuario);
			
			//delete
			dao.excluir(usuario.getId());
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
