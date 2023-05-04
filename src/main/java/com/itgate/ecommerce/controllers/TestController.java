package com.itgate.ecommerce.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('RESPEQUIPE') or hasRole('RESPFEDERATION') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/responsableEquipe")
	@PreAuthorize("hasRole('RESPEQUIPE')")
	public String responsableEquipeAcess() {
		return "responsable equipe  Board.";
	}
	@GetMapping("/responsableFederation")
	@PreAuthorize("hasRole('RESPFEDERATION')")
	public String responsablefederationAccess() {
		return "responsable federation  Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
