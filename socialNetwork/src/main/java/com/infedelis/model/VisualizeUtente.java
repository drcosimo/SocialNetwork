package com.infedelis.model;

import java.sql.Date;

import javax.persistence.Column;

public class VisualizeUtente {
	private String nickname;
	private String nome;
	private String cognome;
	private String email;
	private Date dataNascita;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public VisualizeUtente(String nickname, String nome, String cognome, String email, Date dataNascita) {
		super();
		this.nickname = nickname;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.dataNascita = dataNascita;
	}
	
	
}
