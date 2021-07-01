package br.com.victor.lab.eglicemia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EntityScan(basePackages = {"br.com.victor.lab.eglicemia.model"})
public class EglicemiaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EglicemiaBackendApplication.class, args);
	}

}
