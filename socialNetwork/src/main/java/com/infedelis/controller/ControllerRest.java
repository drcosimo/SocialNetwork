package com.infedelis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infedelis.model.NewPost;
import com.infedelis.model.PatchPost;
import com.infedelis.model.PostDTO;
import com.infedelis.model.PostInRange;
import com.infedelis.model.SearchPost;
import com.infedelis.model.Utente;
import com.infedelis.model.VisualizePost;
import com.infedelis.service.PostService;
import com.infedelis.service.UtenteService;

@RestController
@RequestMapping(path = "", produces = "application/json", consumes = "application/json")
@CrossOrigin(origins = "*")

public class ControllerRest {
	@Autowired
	private UtenteService utenteService;

	@Autowired
	private PostService postService;


	// metodo di login
	@PostMapping(path = "/login")
	public HttpStatus login(@RequestBody Utente u) {
		try {
			return utenteService.login(u.getNickname(),u.getPassword());
		}catch(Exception e) {
			e.printStackTrace();
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}

	// metodo di registrazione
	@PostMapping(path = "/registrazione")
	public HttpStatus registra(@RequestBody Utente u) {
		try {
			return utenteService.registra(u);
		}catch(Exception e) {
			e.printStackTrace();
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}

	@PostMapping(path = "/creaPost")
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

	@PostMapping(path = "/vediPostInRange")
	public ResponseEntity<List<VisualizePost>> vediPostInRange(@RequestBody PostInRange p){
		try {
			// controllo login
			HttpStatus status = utenteService.login(p.getNickname(), p.getPassword());

			// utente autenticato
			if(status == HttpStatus.OK) {
				return postService.vediPostInRange(p.getDataInizio(), p.getDataFine());
			}else {
				return new ResponseEntity<List<VisualizePost>>(HttpStatus.BAD_REQUEST);
			}

		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<VisualizePost>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(path = "/vediPostInRangeUtente")
	public ResponseEntity<List<VisualizePost>> vediPostInRangeUtente(@RequestBody PostInRange p){
		try {
			// controllo login
			HttpStatus status = utenteService.login(p.getNickname(), p.getPassword());

			// utente autenticato
			if(status == HttpStatus.OK) {
				return postService.vediPostInRangeUtente(p);
			}else {
				return new ResponseEntity<List<VisualizePost>>(HttpStatus.BAD_REQUEST);
			}

		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<VisualizePost>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(path = "/cercaTitoloTestoInPost")
	public ResponseEntity<List<VisualizePost>> cercaTitoloTestoInPost(@RequestBody SearchPost sp){
		try {
			// controllo login
			HttpStatus status = utenteService.login(sp.getNickname(), sp.getPassword());

			// utente autenticato
			if(status == HttpStatus.OK) {
				return postService.cercaTitoloTestoInPost(sp.getTitolo(),sp.getTesto());
			}else {
				return new ResponseEntity<List<VisualizePost>>(HttpStatus.BAD_REQUEST);
			}

		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<VisualizePost>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path = "/cercaTitoloTestoInPostUtente")
	public ResponseEntity<List<VisualizePost>> cercaTitoloTestoInPostUtente(@RequestBody SearchPost sp){
		try {
			// controllo login
			HttpStatus status = utenteService.login(sp.getNickname(), sp.getPassword());

			// utente autenticato
			if(status == HttpStatus.OK) {
				return postService.cercaTitoloTestoInPostUtente(sp);
			}else {
				return new ResponseEntity<List<VisualizePost>>(HttpStatus.BAD_REQUEST);
			}

		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<VisualizePost>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PatchMapping(path = "/modificaPost", consumes = "application/json")
	public HttpStatus modificaPost(@RequestBody PatchPost pp) {
		try {
			// controllo login
			HttpStatus status = utenteService.login(pp.getNickname(),pp.getPassword());

			if(status == HttpStatus.OK) {
				return postService.modificaPost(pp);
			}else {
				return HttpStatus.BAD_REQUEST;
			}

		}catch(Exception e) {
			e.printStackTrace();
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}

	@DeleteMapping(path ="/eliminaPost")
	public HttpStatus eliminaPost(@RequestBody PostDTO dp) {
		try {
			// validazione utente
			HttpStatus status = utenteService.login(dp.getNickname(), dp.getPassword());
			
			if(status == HttpStatus.OK) {
				return postService.eliminaPost(dp);
			}else {
				return HttpStatus.BAD_REQUEST;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
	
	@PostMapping(path = "/miPiace")
	public HttpStatus miPiace(@RequestBody PostDTO pd) {
		try {
			// validazione utente
			HttpStatus status = utenteService.login(pd.getNickname(), pd.getPassword());
			System.out.println();
			if(status == HttpStatus.OK) {
				return postService.mettiLike(pd);
			}else {
				return HttpStatus.BAD_REQUEST;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
	
	@PostMapping(path = "/nonMiPiace")
	public HttpStatus nonMiPiace(@RequestBody PostDTO pd) {
		try {
			// validazione utente
			HttpStatus status = utenteService.login(pd.getNickname(), pd.getPassword());
			
			if(status == HttpStatus.OK) {
				return postService.mettiDislike(pd);
			}else {
				return HttpStatus.BAD_REQUEST;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
}