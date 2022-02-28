package com.infedelis.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infedelis.model.Utente;
import com.infedelis.model.VisualizeUtente;
import com.infedelis.repository.UtenteCrudRepository;

@Service("Utente")
@Transactional
public class UtenteService {
	@Autowired
	private UtenteCrudRepository utenteRepo;


	// metodo di login
	public ResponseEntity<VisualizeUtente> login(String nickname, String password) throws Exception{
		Utente u = utenteRepo.getByNicknameAndPassword(nickname,password);
		if(u != null ) {
			VisualizeUtente vu=new VisualizeUtente(u.getNickname(), u.getNome(), u.getCognome(), u.getEmail(), u.getDataNascita());
			System.out.println(HttpStatus.OK);
			
			return new ResponseEntity<VisualizeUtente>(vu, HttpStatus.OK);
		}else {
			return new ResponseEntity<VisualizeUtente>(HttpStatus.BAD_REQUEST);
		}
	}

	// metodo di registrazione
	public ResponseEntity<VisualizeUtente> registra(Utente u) throws Exception{
		// controllo presenza utente
		Utente u1 = utenteRepo.getByNicknameAndPassword(u.getNickname(), u.getPassword());

		// controllo se l'utente è gia presente
		if(u1 == null) {
			// set attivo e isAdmin
			u.setAttivo(true);
			u.setIsAdmin(false);
			
			// inserisco l'utente
			utenteRepo.save(u);
			// restituisco lo status
			VisualizeUtente vu=new VisualizeUtente(u.getNickname(), u.getNome(), u.getCognome(), u.getEmail(), u.getDataNascita());

			return new ResponseEntity<VisualizeUtente>(vu, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<VisualizeUtente>(HttpStatus.BAD_REQUEST);
		}
	}
}