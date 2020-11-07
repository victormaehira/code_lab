package br.com.lab.smartcity.tests;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.smartcity.domain.Cliente;
import br.com.lab.smartcity.domain.ContratoAluguel;
import br.com.lab.smartcity.domain.Estabelecimento;
import br.com.lab.smartcity.domain.TipoEstabelecimento;

public class CadastroEstabelecimentoTipoTest {
	public static void test() {
		EntityManager em = Persistence.createEntityManagerFactory("smartcity").createEntityManager();
		em.getTransaction().begin();
		
		TipoEstabelecimento tipo = new TipoEstabelecimento(0, "PetShop", null);
		
		Cliente cliente = new Cliente(0, "Thiago", null);
		Cliente cliente2 = new Cliente(0, "Leandro", null);
		
		ContratoAluguel contrato = new ContratoAluguel(1, 2000, new GregorianCalendar(2015, Calendar.JANUARY, 10), null);
		
		List<Cliente> lista = new ArrayList<Cliente>();
		lista.add(cliente);
		lista.add(cliente2);
		
		Estabelecimento estabelecimento = new Estabelecimento(0, "Fiap Pet", null, lista, tipo);
		//Estabelecimento estabelecimento = new Estabelecimento(0, "Fiap Pet", null, null, null);
		Estabelecimento estabelecimento2 = new Estabelecimento(0, "Poyatos Dog", contrato, lista, tipo);
		
		contrato.setEstabelecimento(estabelecimento2);
		
		em.persist(estabelecimento);
		em.persist(estabelecimento2);
		
		//Estabelecimento estabelecimento3 = new Estabelecimento(0, "Quero ser inserido", null, null, null);
		//em.merge(estabelecimento3);
		//em.persist(estabelecimento3);
		
		em.getTransaction().commit();
		em.close();
	}
}
