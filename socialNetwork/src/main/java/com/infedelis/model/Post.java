package com.infedelis.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String titolo;
	@Column(nullable = false)
	private String testo;
	@Column(nullable = false)
	private LocalDateTime creazione;
	@Column(nullable = false)
	private LocalDateTime aggiornamento;
	@Column(nullable = false)
	private Integer numLike;
	@Column(nullable = false)
	private Integer numDislike;
	@Column(nullable = false)
	private Boolean attivo;
	
	// un post è pubblicato da un solo utente
	
	@ManyToOne
	@JoinColumn(name = "utente")
	private Utente utente = null;
	
	// like
	@ManyToMany(mappedBy = "liked")
	private List<Utente> miPiace = null;
	
	// dislike
	@ManyToMany(mappedBy = "disliked")
	private List<Utente> nonMiPiace = null;
	
	public Post() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}


	
	public LocalDateTime getCreazione() {
		return creazione;
	}

	public LocalDateTime getAggiornamento() {
		return aggiornamento;
	}

	public void setAggiornamento(LocalDateTime aggiornamento) {
		this.aggiornamento = aggiornamento;
	}

	public void setCreazione(LocalDateTime creazione) {
		this.creazione = creazione;
	}

	public Integer getNumLike() {
		return numLike;
	}

	public void setNumLike(Integer numLike) {
		this.numLike = numLike;
	}

	public Integer getNumDislike() {
		return numDislike;
	}

	public void setNumDislike(Integer numDislike) {
		this.numDislike = numDislike;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public List<Utente> getMiPiace() {
		return miPiace;
	}

	public void setMiPiace(List<Utente> miPiace) {
		this.miPiace = miPiace;
	}

	public List<Utente> getNonMiPiace() {
		return nonMiPiace;
	}

	public void setNonMiPiace(List<Utente> nonMiPiace) {
		this.nonMiPiace = nonMiPiace;
	}

	
	
	
}
