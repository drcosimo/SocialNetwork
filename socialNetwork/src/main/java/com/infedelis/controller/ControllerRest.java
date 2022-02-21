package com.infedelis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.infedelis.model.Utente;
import com.infedelis.service.PostService;
import com.infedelis.service.UtenteService;

@RestController
@RequestMapping(path = "", produces = "application/json")
@CrossOrigin(origins = "*")

public class ControllerRest {
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private PostService postService;
	
	
	// metodo di login
	@PostMapping(path = "/login", consumes = "application/json")
	public HttpStatus login(@RequestBody Utente u) {
		try {
			return utenteService.login(u.getNickname(),u.getPassword());
		}catch(Exception e) {
			e.printStackTrace();
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
	
	// metodo di registrazione
	@PostMapping(path = "/registrazione", consumes = "application/json")
	public HttpStatus registra(@RequestBody Utente u) {
		try {
			return utenteService.registra(u);
		}catch(Exception e) {
			e.printStackTrace();
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
	
}
