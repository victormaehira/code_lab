package br.com.lab.eglicemia.tests;

import java.time.LocalTime;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.eglicemia.dao.AlarmeDAO;
import br.com.lab.eglicemia.dao.TipoDiabetesDAO;
import br.com.lab.eglicemia.dao.UsuarioDAO;
import br.com.lab.eglicemia.domain.Alarme;
import br.com.lab.eglicemia.domain.TipoDiabetes;
import br.com.lab.eglicemia.domain.Usuario;

public class CreateAlarmeTest {
	public static void test() {
		EntityManager em = null;
		try {
			em = Persistence.createEntityManagerFactory("eglicemia").createEntityManager();
			UsuarioDAO usuarioDAO = new UsuarioDAO(em);
			em.getTransaction().begin();
			
			TipoDiabetesDAO tipoDiabetesDAO = new TipoDiabetesDAO(em);
			TipoDiabetes tipoDiabetes = new TipoDiabetes();
			tipoDiabetes.setTipo("GESTACIONAL");
			tipoDiabetesDAO.salvar(tipoDiabetes);
			em.merge(tipoDiabetes);

			Usuario usuario = new Usuario();
			usuario.setNome("Edson Arantes");
			usuario.setEmail("teste@teste.com");
			usuario.setPassword("secreto");
			usuario.setTipoDiabetes(tipoDiabetes);
			
			usuarioDAO.salvar(usuario);
			em.merge(usuario);
			
			AlarmeDAO alarmeDao = new AlarmeDAO(em);
			Alarme alarme = new Alarme();
			alarme.setAtivo(true);
			alarme.setUsuario(usuario);
			
			alarme.setHora(LocalTime.of(11, 30, 0));
			
			alarmeDao.salvar(alarme);
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
