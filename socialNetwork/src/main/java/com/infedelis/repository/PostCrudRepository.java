package com.infedelis.repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.infedelis.model.Post;
import com.infedelis.model.Utente;

public interface PostCrudRepository extends CrudRepository<Post,Integer>{
	//@Query("select p from Post p where p.utente=:u")
	public List<Post> findByUtente(Utente u);
	
	//aggiornamento post dell'utente compresi tra due date
	@Query("select p from Post p where p.utente=:u and p.aggiornamento>=:inizio and p.aggiornamento<=:fine")
	public List<Post> getByNickBetweenDates(@Param("u") Utente u,@Param("inizio") LocalDateTime inizio,@Param("fine") LocalDateTime fine);
	
	//tutti i post compresi tra due date
	public List<Post> getByAggiornamentoBetween(LocalDateTime inizio, LocalDateTime fine);
	
	//cerca post con titolo o testo contenente parole
	public List<Post> findByTitoloContainsOrTestoContains(String testo,String testoUguale);
	
	//cerca post di un utente con titolo o testo contenente parole
	@Query("select p from Post p where p.utente=:u and (p.titolo like %:titolo% or p.testo like %:testo%)")
	public List<Post> getPostUtenteContenenteParole(@Param("u") Utente u,@Param("titolo") String titolo, @Param("testo") String testo);
		
}
