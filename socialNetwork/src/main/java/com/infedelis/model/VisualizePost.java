package com.infedelis.model;

import java.sql.Date;

public class VisualizePost {
	private String titolo;
	private String testo;
	private Date creazione;
	private Date aggiornamento;
	private Integer numLike;
	private Integer numDislike;
	private String nicknameOwner;
	
	
	public VisualizePost(String titolo, String testo, Date creazione, Date aggiornamento, Integer numLike,
			Integer numDislike, String nicknameOwner) {
		super();
		this.titolo = titolo;
		this.testo = testo;
		this.creazione = creazione;
		this.aggiornamento = aggiornamento;
		this.numLike = numLike;
		this.numDislike = numDislike;
		this.nicknameOwner = nicknameOwner;
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
	public Date getCreazione() {
		return creazione;
	}
	public void setCreazione(Date creazione) {
		this.creazione = creazione;
	}
	public Date getAggiornamento() {
		return aggiornamento;
	}
	public void setAggiornamento(Date aggiornamento) {
		this.aggiornamento = aggiornamento;
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
	public String getNicknameOwner() {
		return nicknameOwner;
	}
	public void setNicknameOwner(String nicknameOwner) {
		this.nicknameOwner = nicknameOwner;
	}
	
	
}
