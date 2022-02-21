package com.infedelis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.infedelis.model.NewPost;
import com.infedelis.model.Post;
import com.infedelis.model.Utente;
import com.infedelis.model.VisualizePost;
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

	@PostMapping(path = "/creaPost", consumes = "application/json")
	public HttpStatus creaPost(@RequestBody NewPost np) {
		try {
			// controllo login
			HttpStatus status = utenteService.login(np.getNickname(), np.getPassword());

			// utente autenticato
			if(status == HttpStatus.OK) {
				return postService.creaPost(np);
			}else {
				return status;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}

	@PostMapping(path = "/vediTuttiPost")
	public ResponseEntity<List<VisualizePost>> vediTuttiPost(@RequestBody Utente u){
		try {
			// controllo login
			HttpStatus status = utenteService.login(u.getNickname(), u.getPassword());

			// utente autenticato
			if(status == HttpStatus.OK) {
				return postService.vediTuttiPost();
			}else {
				return new ResponseEntity<List<VisualizePost>>(HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<VisualizePost>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path = "/vediPostUtente")
	public ResponseEntity<List<VisualizePost>> vediPostUtente(@RequestBody Utente u){
		try {
			// controllo login
			HttpStatus status = utenteService.login(u.getNickname(), u.getPassword());

			// utente autenticato
			if(status == HttpStatus.OK) {
				return postService.vediPostUtente(u);
			}else {
				return new ResponseEntity<List<VisualizePost>>(HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<VisualizePost>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
