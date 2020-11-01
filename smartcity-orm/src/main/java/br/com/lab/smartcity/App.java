package br.com.lab.smartcity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {
        EntityManager em = null;
        try {
		em =  Persistence.
                createEntityManagerFactory("smartcity").
                createEntityManager();

        } catch (Exception e) {

		if (em != null && em.getTransaction().isActive()) {
		   em.getTransaction().rollback();
	     }
		e.printStackTrace();

        }

	   if (em != null) {
		em.close();
	   }
	   System.exit(0);
	}
}


	
