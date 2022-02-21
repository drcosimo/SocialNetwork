package com.infedelis.repository;

import org.springframework.data.repository.CrudRepository;

import com.infedelis.model.Utente;

public interface UtenteCrudRepository extends CrudRepository<Utente, Integer> {

	public Utente getByNicknameAndPassword(String username, String password);
	
}
