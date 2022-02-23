package com.infedelis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.infedelis.model.Utente;

public interface UtenteCrudRepository extends CrudRepository<Utente, Integer> {

	@Query("select u from Utente u where u.nickname=:n and u.password=:p and u.attivo=1")
	public Utente getByNicknameAndPassword(@Param("n") String nickname, @Param("p")String password);
}
