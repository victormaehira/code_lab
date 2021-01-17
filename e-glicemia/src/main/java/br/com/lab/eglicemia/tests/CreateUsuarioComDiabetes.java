package br.com.lab.eglicemia.tests;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.eglicemia.dao.UsuarioDAO;
import br.com.lab.eglicemia.domain.Alarme;
import br.com.lab.eglicemia.domain.Glicemia;
import br.com.lab.eglicemia.domain.TipoDiabetes;
import br.com.lab.eglicemia.domain.Usuario;

public class CreateUsuarioComDiabetes {
	private static final boolean ALARME_ATIVO = true;
	private static final int ONZE_HORAS = 11;
	private static final int TRINTA_MINUTOS = 30;
	private static final int DEZENOVE_HORAS = 19;
	public static void test() {
		EntityManager em = null;
		try {
			em = Persistence.createEntityManagerFactory("eglicemia").createEntityManager();
			UsuarioDAO dao = new UsuarioDAO(em);
			em.getTransaction().begin();
			
			System.out.println("Teste de Insercao:");
			System.out.println("Criando TipoDiabetes.");
			TipoDiabetes tipoDiabetes = new TipoDiabetes("GESTACIONAL");
			
			System.out.println("Criando Alarmes.");
			Alarme alarmeColetaAntesDoAlmoco = new Alarme(ALARME_ATIVO, LocalTime.of(ONZE_HORAS, TRINTA_MINUTOS, 0));
			Alarme alarmeColetaAntesDaJanta = new Alarme(ALARME_ATIVO, LocalTime.of(DEZENOVE_HORAS, TRINTA_MINUTOS, 0));
			List<Alarme> alarmes = new ArrayList<Alarme>();
			alarmes.add(alarmeColetaAntesDoAlmoco);
			alarmes.add(alarmeColetaAntesDaJanta);
			
			System.out.println("Criando Resultados de Glicemia.");
			Glicemia glicemiaAntesDoAlmoco = new Glicemia(220f, new GregorianCalendar(2021, Calendar.JANUARY, Calendar.DAY_OF_MONTH, ONZE_HORAS, TRINTA_MINUTOS)); 
			Glicemia glicemiaAntesDaJanta = new Glicemia(130f, new GregorianCalendar(2021, Calendar.JANUARY, Calendar.DAY_OF_MONTH, DEZENOVE_HORAS, TRINTA_MINUTOS));
			List<Glicemia> glicemias = new ArrayList<Glicemia>();
			glicemias.add(glicemiaAntesDoAlmoco);
			glicemias.add(glicemiaAntesDaJanta);
			
			System.out.println("Criando Usuario");
			Usuario usuario = new Usuario("Jose Mafalda", "mafalda@teste.com", "senha", tipoDiabetes, glicemias, alarmes);
			
			alarmeColetaAntesDoAlmoco.setUsuario(usuario);
			alarmeColetaAntesDaJanta.setUsuario(usuario);
			glicemiaAntesDoAlmoco.setUsuario(usuario);
			glicemiaAntesDaJanta.setUsuario(usuario);
			
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
