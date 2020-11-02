package br.com.lab.smartcity;

import br.com.lab.smartcity.tests.CreateEntityManagerTest;
import br.com.lab.smartcity.tests.CreateEstabelecimentoTest;
import br.com.lab.smartcity.tests.DeleteEstabelecimentoTest;
import br.com.lab.smartcity.tests.FindEstabelecimentoTest;
import br.com.lab.smartcity.tests.UpdateEstabelecimentoTest;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {
		CreateEntityManagerTest.test();
		CreateEstabelecimentoTest.test();
		FindEstabelecimentoTest.test();
		UpdateEstabelecimentoTest.test();
		DeleteEstabelecimentoTest.test();
		System.out.println("Fim");
		System.exit(0);
	}
}


	
