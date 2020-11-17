package br.com.lab.smartcity.tests;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.smartcity.domain.Carro;
import br.com.lab.smartcity.domain.Moto;
import br.com.lab.smartcity.domain.Veiculo;

public class CreateSingleTableTest {
	public static void test() {
		System.out.println(">CreateTablePerClassTest");
		
		EntityManager em = null;
		try {
			em = Persistence.createEntityManagerFactory("smartcity").createEntityManager();

			em.getTransaction().begin();
			
			Veiculo veiculo = new Veiculo();
			veiculo.setNome("Sou Veiculo");
			
			Carro carro = new Carro();
			carro.setNome("Sou carro");
			carro.setNumPortas(4);
			
			Moto moto = new Moto();
			moto.setNome("Sou moto");
			moto.setTipo("Esportiva");
			
			em.persist(veiculo);
			em.persist(carro);
			em.persist(moto);
			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}
}
