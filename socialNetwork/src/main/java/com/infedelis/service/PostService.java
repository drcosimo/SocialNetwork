package com.infedelis.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infedelis.model.NewPost;
import com.infedelis.model.Post;
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
		p.setCreazione(Date.valueOf(LocalDate.now()));
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

}
