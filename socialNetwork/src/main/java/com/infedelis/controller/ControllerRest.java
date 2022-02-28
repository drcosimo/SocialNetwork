package com.infedelis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infedelis.model.NewPost;
import com.infedelis.model.PatchPost;
import com.infedelis.model.PostDTO;
import com.infedelis.model.PostInRange;
import com.infedelis.model.SearchPost;
import com.infedelis.model.Utente;
import com.infedelis.model.VisualizePost;
import com.infedelis.model.VisualizeUtente;
import com.infedelis.service.PostService;
import com.infedelis.service.UtenteService;

@RestController
@RequestMapping(path = "", produces = "application/json", consumes = "application/json")
@CrossOrigin(origins="*",allowedHeaders = "*",exposedHeaders = "*")
public class ControllerRest {
	@Autowired
	private UtenteService utenteService;

	@Autowired
	private PostService postService;


	// metodo di login
	@PostMapping(path = "/login")	
	public ResponseEntity<VisualizeUtente> login(@RequestBody Utente u) {
		try {
			return utenteService.login(u.getNickname(),u.getPassword());
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<VisualizeUtente>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// metodo di registrazione
	@PostMapping(path = "/registrazione")
	public ResponseEntity<VisualizeUtente> registra(@RequestBody Utente u) {
		try {
			return utenteService.registra(u);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<VisualizeUtente>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "/creaPost")
	public ResponseEntity<VisualizePost> creaPost(@RequestBody NewPost np) {
		try {
			// controllo login
			
			if(utenteService.login(np.getNickname(), np.getPassword()).getStatusCode() == HttpStatus.OK) {
				// utente autenticato
				return postService.creaPost(np);
			}else {
				return new ResponseEntity<VisualizePost>(HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<VisualizePost>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path = "/vediTuttiPost")
	public ResponseEntity<List<VisualizePost>> vediTuttiPost(@RequestBody Utente u){
		try {
			// controllo login
			
			if(utenteService.login(u.getNickname(), u.getPassword()).getStatusCode() == HttpStatus.OK) {
				// utente autenticato
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
			if(utenteService.login(u.getNickname(), u.getPassword()).getStatusCode() == HttpStatus.OK) {				
				// utente autenticato
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
			if(utenteService.login(p.getNickname(), p.getPassword()).getStatusCode() == HttpStatus.OK) {
				// utente autenticato
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
			if(utenteService.login(p.getNickname(), p.getPassword()).getStatusCode() == HttpStatus.OK) {
				// utente autenticato
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
			// utente autenticato
			if(utenteService.login(sp.getNickname(), sp.getPassword()).getStatusCode() == HttpStatus.OK) {
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

			if(utenteService.login(sp.getNickname(), sp.getPassword()).getStatusCode() == HttpStatus.OK) {
				// utente autenticato
				return postService.cercaTitoloTestoInPostUtente(sp);
			}else {
				return new ResponseEntity<List<VisualizePost>>(HttpStatus.BAD_REQUEST);
			}

		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<VisualizePost>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping(path = "/modificaPost", consumes = "application/json")
	public ResponseEntity<VisualizePost> modificaPost(@RequestBody PatchPost pp) {
		try {
			// controllo login

			if(utenteService.login(pp.getNickname(),pp.getPassword()).getStatusCode() == HttpStatus.OK) {
				//se utente autenticato
				return postService.modificaPost(pp);
			}else {
				return new ResponseEntity<VisualizePost>(HttpStatus.BAD_REQUEST);
			}

		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<VisualizePost>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path ="/eliminaPost")
	public ResponseEntity<VisualizePost> eliminaPost(@RequestBody PostDTO dp) {
		try {
			// validazione utente
			if(utenteService.login(dp.getNickname(), dp.getPassword()).getStatusCode() == HttpStatus.OK) {
				//se utente autenticato
				return postService.eliminaPost(dp);
			}else {
				return new ResponseEntity<VisualizePost>(HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<VisualizePost>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path = "/miPiace")
	public ResponseEntity<VisualizePost> miPiace(@RequestBody PostDTO pd) {
		try {
			// validazione utente
			if(utenteService.login(pd.getNickname(), pd.getPassword()).getStatusCode() == HttpStatus.OK) {
				//se utente autenticato
				return postService.mettiLike(pd);
			}else {
				return new ResponseEntity<VisualizePost>(HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<VisualizePost>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path = "/nonMiPiace")
	public ResponseEntity<VisualizePost> nonMiPiace(@RequestBody PostDTO pd) {
		try {
			// validazione utente
			if(utenteService.login(pd.getNickname(), pd.getPassword()).getStatusCode() == HttpStatus.OK) {
				//se utente autenticato
				return postService.mettiDislike(pd);
			}else {
				return new ResponseEntity<VisualizePost>(HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<VisualizePost>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}