package com.infedelis.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infedelis.model.Utente;
import com.infedelis.repository.UtenteCrudRepository;

@Service("Utente")
@Transactional
public class UtenteService {
	@Autowired
	private UtenteCrudRepository utenteRepo;


	// metodo di login
	public HttpStatus login(String username, String password) throws Exception{
		Utente u = utenteRepo.getByNicknameAndPassword(username,password);

		if(u != null ) {
			return HttpStatus.OK;
		}else {
			return HttpStatus.BAD_REQUEST;
		}
	}

	// metodo di registrazione
	public HttpStatus registra(Utente u) throws Exception{
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
			return HttpStatus.CREATED;
		}else {
			return HttpStatus.BAD_REQUEST;
		}
	}
}