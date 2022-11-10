package com.gke.victor.poc.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BioController {

	@GetMapping("/biometric")
	public String getBio() {
		return "Testing...";
	}

}
