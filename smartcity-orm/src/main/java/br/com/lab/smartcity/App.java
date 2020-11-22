package br.com.lab.smartcity;

import br.com.lab.smartcity.tests.CadastroEstabelecimentoTipoTest;
import br.com.lab.smartcity.tests.CreateAvaliacaoIdTest;
import br.com.lab.smartcity.tests.CreateEntityManagerTest;
import br.com.lab.smartcity.tests.CreateEstabelecimentoTest;
import br.com.lab.smartcity.tests.CreatePessoaTest;
import br.com.lab.smartcity.tests.CreateSingleTableTest;
import br.com.lab.smartcity.tests.CreateTablePerClassTest;
import br.com.lab.smartcity.tests.DeleteEstabelecimentoTest;
import br.com.lab.smartcity.tests.FindEstabelecimentoTest;
import br.com.lab.smartcity.tests.GenericDAOTest;
import br.com.lab.smartcity.tests.TipoEstabelecimentoDaoListOrdenadoPorNomeTest;
import br.com.lab.smartcity.tests.TipoEstabelecimentoDaoListTest;
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
		CadastroEstabelecimentoTipoTest.test();
		CreatePessoaTest.test();
		CreateAvaliacaoIdTest.test();
		CreateTablePerClassTest.test();
		CreateSingleTableTest.test();
		GenericDAOTest.test();
		TipoEstabelecimentoDaoListTest.test();
		TipoEstabelecimentoDaoListOrdenadoPorNomeTest.test();
		System.out.println("Fim");
		System.exit(0);
	}
}


	
