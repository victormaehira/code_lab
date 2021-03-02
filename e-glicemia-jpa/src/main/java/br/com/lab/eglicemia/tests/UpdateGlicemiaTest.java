package br.com.lab.eglicemia.tests;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.eglicemia.dao.GlicemiaDAO;
import br.com.lab.eglicemia.dao.UsuarioDAO;
import br.com.lab.eglicemia.domain.Glicemia;
import br.com.lab.eglicemia.domain.Usuario;

public class UpdateGlicemiaTest {

	public static void test() {
		EntityManager em = null;
		try {
			em = Persistence.createEntityManagerFactory("eglicemia").createEntityManager();
			em.getTransaction().begin();
			
			UsuarioDAO dao = new UsuarioDAO(em);
			Usuario usuario = new Usuario();
			usuario.setNome("Edson Arantes");
			dao.salvar(usuario);
			
			GlicemiaDAO glicemiaDao = new GlicemiaDAO(em);
			Glicemia glicemia = new Glicemia();
			glicemia.setValor(220.0f);
			glicemia.setData(new GregorianCalendar(2020, Calendar.NOVEMBER, 22));
			glicemia.setUsuario(usuario);
			glicemiaDao.salvar(glicemia);
			
			//update
			glicemia.setValor(330.0f);
			glicemia.setData(new GregorianCalendar(2021, Calendar.DECEMBER, 27));
			glicemiaDao.salvar(glicemia);
			
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
