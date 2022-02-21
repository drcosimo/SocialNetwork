package com.infedelis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.infedelis.model.Post;
import com.infedelis.model.Utente;

public interface PostCrudRepository extends CrudRepository<Post,Integer>{
	//@Query("select p from Post p where p.utente=:u")
	public List<Post> findByUtente(Utente u);
}
