package com.lab.victor.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.lab.victor.api.config.property.CryptoApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(CryptoApiProperty.class)
public class CryptoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoApiApplication.class, args);
	}
}
