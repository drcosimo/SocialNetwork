package com.infedelis.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infedelis.model.PostDTO;
import com.infedelis.model.NewPost;
import com.infedelis.model.PatchPost;
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

	public ResponseEntity<VisualizePost> creaPost(NewPost np) throws Exception {
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
		
		VisualizePost vp=new VisualizePost(p.getId(), p.getTitolo(), p.getTesto(), p.getCreazione(), p.getAggiornamento(), p.getNumLike(), p.getNumDislike(), p.getUtente().getNickname());
		return new ResponseEntity<VisualizePost>(vp, HttpStatus.CREATED);
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
						new VisualizePost(p.getId(),p.getTitolo(),p.getTesto(),p.getCreazione(),p.getAggiornamento(),p.getNumLike(), p.getNumDislike(),p.getUtente().getNickname()));
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
						new VisualizePost(p.getId(),p.getTitolo(),p.getTesto(),p.getCreazione(),p.getAggiornamento(),p.getNumLike(), p.getNumDislike(),u.getNickname()));
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
						new VisualizePost(p.getId(),p.getTitolo(),p.getTesto(),p.getCreazione(),p.getAggiornamento(),p.getNumLike(), p.getNumDislike(),p.getUtente().getNickname()));
			}

			// restituisco la lista di post insieme al messaggio OK
			return new ResponseEntity<List<VisualizePost>>(postDaRestituire,HttpStatus.OK);
		}else {
			// altrimenti not found
			return new ResponseEntity<List<VisualizePost>>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<VisualizePost>> vediPostInRangeUtente(PostInRange pr) throws Exception{
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
						new VisualizePost(p.getId(),p.getTitolo(),p.getTesto(),p.getCreazione(),p.getAggiornamento(),p.getNumLike(), p.getNumDislike(),p.getUtente().getNickname()));
			}

			// restituisco la lista di post insieme al messaggio OK
			return new ResponseEntity<List<VisualizePost>>(postDaRestituire,HttpStatus.OK);
		}else {
			// altrimenti not found
			return new ResponseEntity<List<VisualizePost>>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<VisualizePost>> cercaTitoloTestoInPost(String titolo, String testo) throws Exception{
		// ottengo la lista dei post che contengono parole specificate nel titolo o testo
		List<Post> posts = (List<Post>) postRepo.findByTitoloContainsOrTestoContains(titolo, testo);

		// se ci sono dei post li restituisco
		if(posts != null && posts.size() > 0) {
			// effettuo la conversione da Post a VisualizePost
			List<VisualizePost> postDaRestituire = new ArrayList<VisualizePost>();

			for(Post p : posts) {
				postDaRestituire.add(
						new VisualizePost(p.getId(),p.getTitolo(),p.getTesto(),p.getCreazione(),p.getAggiornamento(),p.getNumLike(), p.getNumDislike(),p.getUtente().getNickname()));
			}

			// restituisco la lista di post insieme al messaggio OK
			return new ResponseEntity<List<VisualizePost>>(postDaRestituire,HttpStatus.OK);
		}else {
			// altrimenti not found
			return new ResponseEntity<List<VisualizePost>>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<VisualizePost>> cercaTitoloTestoInPostUtente(SearchPost sp) throws Exception{
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
						new VisualizePost(p.getId(),p.getTitolo(),p.getTesto(),p.getCreazione(),p.getAggiornamento(),p.getNumLike(), p.getNumDislike(),p.getUtente().getNickname()));
			}

			// restituisco la lista di post insieme al messaggio OK
			return new ResponseEntity<List<VisualizePost>>(postDaRestituire,HttpStatus.OK);
		}else {
			// altrimenti not found
			return new ResponseEntity<List<VisualizePost>>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<VisualizePost> modificaPost(PatchPost pp) throws Exception{
		// ottengo il riferimento all'utente
		Utente u = utenteRepo.getByNicknameAndPassword(pp.getNickname(), pp.getPassword());

		// ottengo il riferimento al post
		Post p = postRepo.getPostById(pp.getId());

		// controllo che il post esiste e che l'utente sia il proprietario
		if(p != null && p.getUtente().getId() == u.getId()) {
			//contatore; controllo che almeno uno dei due campi (titolo, testo) sia cambiato
			boolean cambiato=false;
			// aggiorno il post
			// se il campo in entrata è vuoto non lo aggiorno
			if(!pp.getTitolo().equals("")) {
				p.setTitolo(pp.getTitolo());
				cambiato=true;
			}
			// se il campo in entrata è vuoto non lo aggiorno
			if(!pp.getTesto().equals("")) {
				p.setTesto(pp.getTesto());
				cambiato=true;
			}
			//aggiorno ora dell'ultimo aggiornamento
			p.setAggiornamento(LocalDateTime.now());

			// effettuo l'aggiornamento sul db
			//postRepo.save(p);
			VisualizePost vp=new VisualizePost(p.getId(), p.getTitolo(), p.getTesto(), p.getCreazione(), p.getAggiornamento(), p.getNumLike(), p.getNumDislike(), p.getUtente().getNickname());
			return new ResponseEntity<VisualizePost>(vp, HttpStatus.OK);
		}else {
			return new ResponseEntity<VisualizePost>(HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<VisualizePost> eliminaPost(PostDTO dp) throws Exception{
		// ottengo il riferimento all'utente
		Utente u = utenteRepo.getByNicknameAndPassword(dp.getNickname(), dp.getPassword());

		// ottengo il riferimento al post
		Post p = postRepo.getPostById(dp.getId());

		// controllo permessi di eliminazione
		if(u.getIsAdmin() || p.getUtente().getId() == u.getId()) {
			// elimino il post logicamente(attivo = 0)
			p.setAttivo(false);

			//postRepo.save(p);
			VisualizePost vp=new VisualizePost(p.getId(), p.getTitolo(), p.getTesto(), p.getCreazione(), p.getAggiornamento(), p.getNumLike(), p.getNumDislike(), p.getUtente().getNickname());
			return new ResponseEntity<VisualizePost>(vp, HttpStatus.OK);
		}else {
			return new ResponseEntity<VisualizePost>(HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<VisualizePost> mettiLike(PostDTO pd) throws Exception{
		// ottengo il riferimento all'utente
		Utente u = utenteRepo.getByNicknameAndPassword(pd.getNickname(), pd.getPassword());

		// ottengo il riferimento al post
		Post p = postRepo.getPostById(pd.getId());

		// controllo che l'utente possa mettere like
		if(u.getId() != p.getUtente().getId() && !(u.getLiked().contains(p))) {
			// controllo la presenza nei non mi piace
			if(u.getDisliked().contains(p)) {
				// elimino il non mi piace
				u.getDisliked().remove(p);
				// decremento il numero dei non mi piace
				p.setNumDislike(p.getNumDislike()-1);
			}

			// aggiungo l'utente alla lista dei mi piace
			//p.getMiPiace().add(u);
			u.getLiked().add(p);

			// incremento il contatore dei like
			p.setNumLike(p.getNumLike()+1);

			// aggiorno il db
			//postRepo.save(p);
			//utenteRepo.save(u);
			VisualizePost vp=new VisualizePost(p.getId(), p.getTitolo(), p.getTesto(), p.getCreazione(), p.getAggiornamento(), p.getNumLike(), p.getNumDislike(), p.getUtente().getNickname());
			return new ResponseEntity<VisualizePost>(vp, HttpStatus.OK);
		}else {
			return new ResponseEntity<VisualizePost>(HttpStatus.BAD_REQUEST);
		}

	}
	public ResponseEntity<VisualizePost> mettiDislike(PostDTO pd) throws Exception{
		// ottengo il riferimento all'utente
		Utente u = utenteRepo.getByNicknameAndPassword(pd.getNickname(), pd.getPassword());

		// ottengo il riferimento al post
		Post p = postRepo.getPostById(pd.getId());

		// controllo che l'utente possa mettere dislike
		if(u.getId() != p.getUtente().getId() && !(u.getDisliked().contains(p))) {
			// controllo la presenza nei mi piace
			if(u.getLiked().contains(p)) {
				// elimino il mi piace
				u.getLiked().remove(p);
				// decremento il numero dei mi piace
				p.setNumLike(p.getNumLike()-1);
			}

			// aggiungo l'utente alla lista dei non mi piace
			//p.getNonMiPiace().add(u);
			u.getDisliked().add(p);

			// incremento il contatore dei dislike
			p.setNumDislike(p.getNumDislike()+1);

			// aggiorno il db
			//postRepo.save(p);

			VisualizePost vp=new VisualizePost(p.getId(), p.getTitolo(), p.getTesto(), p.getCreazione(), p.getAggiornamento(), p.getNumLike(), p.getNumDislike(), p.getUtente().getNickname());
			return new ResponseEntity<VisualizePost>(vp, HttpStatus.OK);
		}else {
			return new ResponseEntity<VisualizePost>(HttpStatus.BAD_REQUEST);
		}

	}
}
