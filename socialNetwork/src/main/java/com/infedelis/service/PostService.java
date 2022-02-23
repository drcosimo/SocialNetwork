package com.infedelis.service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infedelis.model.NewPost;
import com.infedelis.model.Post;
import com.infedelis.model.PostInRange;
import com.infedelis.model.SearchPost;
import com.infedelis.model.Utente;
import com.infedelis.model.VisualizePost;
import com.infedelis.repository.PostCrudRepository;
import com.infedelis.repository.UtenteCrudRepository;

@Service("Post")
@Transactional
public class PostService {
	@Autowired
	private PostCrudRepository postRepo;

	@Autowired
	private UtenteCrudRepository utenteRepo;

	public HttpStatus creaPost(NewPost np) throws Exception {
		// ottengo il riferimento dal db dell' utente
		Utente u = utenteRepo.getByNicknameAndPassword(np.getNickname(), np.getPassword());

		// creazione nuovo post
		Post p = new Post();

		p.setTitolo(np.getTitolo());
		p.setTesto(np.getTesto());
		p.setAttivo(true);
		p.setCreazione(LocalDateTime.now());
		p.setAggiornamento(p.getCreazione());
		p.setNumLike(0);
		p.setNumDislike(0);

		// imposto la dipendenza sul post
		p.setUtente(u);

		// creo il post
		postRepo.save(p);

		// post creato
		return HttpStatus.CREATED;
	}

	public ResponseEntity<List<VisualizePost>> vediTuttiPost() throws Exception{
		// ottengo la lista dei post
		List<Post> posts = (List<Post>) postRepo.findAll();

		// se ci sono post li restituisco
		if(posts != null && posts.size() > 0) {

			// conversione da Post a VisualizePost
			List<VisualizePost> postDaRestituire = new ArrayList<VisualizePost>();

			for(Post p : posts) {
				postDaRestituire.add(
						new VisualizePost(p.getTitolo(),p.getTesto(),p.getCreazione(),p.getAggiornamento(),p.getNumLike(), p.getNumDislike(),p.getUtente().getNickname()));
			}

			return new ResponseEntity<List<VisualizePost>>(postDaRestituire, HttpStatus.OK);

		}else {
			// altrimenti not found
			return new ResponseEntity<List<VisualizePost>>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<VisualizePost>> vediPostUtente(Utente u) throws Exception{
		// ottengo il riferimento dal db dell' utente
		Utente u1 = utenteRepo.getByNicknameAndPassword(u.getNickname(),u.getPassword());

		// ottengo la lista dei post
		List<Post> posts = (List<Post>) postRepo.findByUtente(u1);

		// se ci sono post li restituisco
		if(posts != null && posts.size() > 0) {
			// conversione da Post a VisualizePost
			List<VisualizePost> postDaRestituire = new ArrayList<VisualizePost>();

			for(Post p : posts) {
				postDaRestituire.add(
						new VisualizePost(p.getTitolo(),p.getTesto(),p.getCreazione(),p.getAggiornamento(),p.getNumLike(), p.getNumDislike(),u.getNickname()));
			}

			return new ResponseEntity<List<VisualizePost>>(postDaRestituire, HttpStatus.OK);

		}else {
			// altrimenti not found
			return new ResponseEntity<List<VisualizePost>>(HttpStatus.NOT_FOUND);
		}
	}


	public ResponseEntity<List<VisualizePost>> vediPostInRange(LocalDateTime dataInizio, LocalDateTime dataFine) throws Exception{

		// ottengo la lista dei post nel range specificato
		List<Post> posts = (List<Post>) postRepo.getByAggiornamentoBetween(dataInizio, dataFine);

		// se ci sono dei post li restituisco
		if(posts != null && posts.size() > 0) {
			// effettuo la conversione da Post a VisualizePost
			List<VisualizePost> postDaRestituire = new ArrayList<VisualizePost>();

			for(Post p : posts) {
				postDaRestituire.add(
						new VisualizePost(p.getTitolo(),p.getTesto(),p.getCreazione(),p.getAggiornamento(),p.getNumLike(), p.getNumDislike(),p.getUtente().getNickname()));
			}

			// restituisco la lista di post insieme al messaggio OK
			return new ResponseEntity<List<VisualizePost>>(postDaRestituire,HttpStatus.OK);
		}else {
			// altrimenti not found
			return new ResponseEntity<List<VisualizePost>>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<VisualizePost>> vediPostInRangeUtente(PostInRange pr){
		// ottengo il riferimento all'utente
		Utente u = utenteRepo.getByNicknameAndPassword(pr.getNickname(), pr.getPassword());

		// ottengo la lista dei post nel range specificato
		List<Post> posts = (List<Post>) postRepo.getByNickBetweenDates(u, pr.getDataInizio(), pr.getDataFine());

		// se ci sono dei post li restituisco
		if(posts != null && posts.size() > 0) {
			// effettuo la conversione da Post a VisualizePost
			List<VisualizePost> postDaRestituire = new ArrayList<VisualizePost>();

			for(Post p : posts) {
				postDaRestituire.add(
						new VisualizePost(p.getTitolo(),p.getTesto(),p.getCreazione(),p.getAggiornamento(),p.getNumLike(), p.getNumDislike(),p.getUtente().getNickname()));
			}

			// restituisco la lista di post insieme al messaggio OK
			return new ResponseEntity<List<VisualizePost>>(postDaRestituire,HttpStatus.OK);
		}else {
			// altrimenti not found
			return new ResponseEntity<List<VisualizePost>>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<VisualizePost>> cercaTitoloTestoInPost(String titolo, String testo){
		// ottengo la lista dei post che contengono parole specificate nel titolo o testo
		List<Post> posts = (List<Post>) postRepo.findByTitoloContainsOrTestoContains(titolo, testo);

		// se ci sono dei post li restituisco
		if(posts != null && posts.size() > 0) {
			// effettuo la conversione da Post a VisualizePost
			List<VisualizePost> postDaRestituire = new ArrayList<VisualizePost>();

			for(Post p : posts) {
				postDaRestituire.add(
						new VisualizePost(p.getTitolo(),p.getTesto(),p.getCreazione(),p.getAggiornamento(),p.getNumLike(), p.getNumDislike(),p.getUtente().getNickname()));
			}

			// restituisco la lista di post insieme al messaggio OK
			return new ResponseEntity<List<VisualizePost>>(postDaRestituire,HttpStatus.OK);
		}else {
			// altrimenti not found
			return new ResponseEntity<List<VisualizePost>>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<VisualizePost>> cercaTitoloTestoInPostUtente(SearchPost sp){
		// ottengo il riferimento all'utente
		Utente u = utenteRepo.getByNicknameAndPassword(sp.getNickname(), sp.getPassword());
		
		// ottengo la lista dei post che contengono parole specificate nel titolo o testo appartenenti all'utente
		List<Post> posts = (List<Post>) postRepo.getPostUtenteContenenteParole(u, sp.getTitolo(),sp.getTesto());

		// se ci sono dei post li restituisco
		if(posts != null && posts.size() > 0) {
			// effettuo la conversione da Post a VisualizePost
			List<VisualizePost> postDaRestituire = new ArrayList<VisualizePost>();

			for(Post p : posts) {
				postDaRestituire.add(
						new VisualizePost(p.getTitolo(),p.getTesto(),p.getCreazione(),p.getAggiornamento(),p.getNumLike(), p.getNumDislike(),p.getUtente().getNickname()));
			}

			// restituisco la lista di post insieme al messaggio OK
			return new ResponseEntity<List<VisualizePost>>(postDaRestituire,HttpStatus.OK);
		}else {
			// altrimenti not found
			return new ResponseEntity<List<VisualizePost>>(HttpStatus.NOT_FOUND);
		}

	}
}
