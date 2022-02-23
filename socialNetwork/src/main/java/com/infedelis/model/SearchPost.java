package com.infedelis.model;

public class SearchPost {
	
	private String nickname;
	private String password;
	private String titolo;
	private String testo;
	public SearchPost(String nickname, String password, String titolo, String testo) {
		super();
		this.nickname = nickname;
		this.password = password;
		this.titolo = titolo;
		this.testo = testo;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	
}
