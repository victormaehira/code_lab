package br.com.lab.eglicemia;

import br.com.lab.eglicemia.tests.CreateAlarmeTest;
import br.com.lab.eglicemia.tests.CreateGlicemiaTest;
import br.com.lab.eglicemia.tests.CreateTipoDiabetesTest;
import br.com.lab.eglicemia.tests.CreateUsuarioComDiabetes;
import br.com.lab.eglicemia.tests.CreateUsuarioTest;
import br.com.lab.eglicemia.tests.DeleteAlarmeTest;
import br.com.lab.eglicemia.tests.DeleteGlicemiaDoAnoPassado;
import br.com.lab.eglicemia.tests.DeleteGlicemiaPorDataTest;
import br.com.lab.eglicemia.tests.DeleteGlicemiaTest;
import br.com.lab.eglicemia.tests.DeleteTipoDiabetesTest;
import br.com.lab.eglicemia.tests.DeleteUsuarioTest;
import br.com.lab.eglicemia.tests.ReadUsuarioByNameTest;
import br.com.lab.eglicemia.tests.ReadUsuarioByTipoDiabetesTest;
import br.com.lab.eglicemia.tests.UpdateAlarmeTest;
import br.com.lab.eglicemia.tests.UpdateGlicemiaTest;
import br.com.lab.eglicemia.tests.UpdateTipoDiabetesTest;
import br.com.lab.eglicemia.tests.UpdateUsuarioTest;

public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("Teste CRUD para todas entidades do dominio:");
    	
    	CreateTipoDiabetesTest.test();
    	CreateUsuarioTest.test();
    	CreateGlicemiaTest.test();
    	CreateAlarmeTest.test();
    	CreateUsuarioComDiabetes.test();
    	
    	ReadUsuarioByNameTest.test();
    	ReadUsuarioByTipoDiabetesTest.test();
    	
    	UpdateTipoDiabetesTest.test();
    	UpdateUsuarioTest.test();
    	UpdateGlicemiaTest.test();
    	UpdateAlarmeTest.test();
    	
    	DeleteTipoDiabetesTest.test();
    	DeleteUsuarioTest.test();
    	DeleteGlicemiaTest.test();
    	DeleteGlicemiaPorDataTest.test();
    	DeleteGlicemiaDoAnoPassado.test();
    	DeleteAlarmeTest.test();
    	
		System.exit(0);
    }
}
