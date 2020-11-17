package br.com.lab.smartcity.tests;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.lab.smartcity.domain.Animal;
import br.com.lab.smartcity.domain.Cachorro;
import br.com.lab.smartcity.domain.Passaro;

public class CreateTablePerClassTest {
	public static void test() {
		System.out.println(">CreateTablePerClassTest");
		
		EntityManager em = null;
		try {
			em = Persistence.createEntityManagerFactory("smartcity").createEntityManager();

			em.getTransaction().begin();
			
			Animal animal = new Animal();
			animal.setNome("Sou animal");
			
			Cachorro cachorro = new Cachorro();
			cachorro.setNome("AU AU");
			cachorro.setNum_patas(4);
			
			Passaro passaro = new Passaro();
			passaro.setNome("PIU");
			passaro.setNum_asas(2);
			
			em.persist(animal);
			em.persist(cachorro);
			em.persist(passaro);
			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}
}
