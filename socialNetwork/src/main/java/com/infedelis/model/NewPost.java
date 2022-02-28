package com.infedelis.model;

public class NewPost {
	private String nickname;
	private String password;
	private String titolo;
	private String testo;
	public NewPost(String nickname, String password, String titolo, String testo) {
		super();
		this.nickname = nickname;
		this.password = password;
		this.titolo = titolo;
		this.testo = testo;
	}
	public String getNickname() {
		return nickname;
	}
	public String getPassword() {
		return password;
	}
	public String getTitolo() {
		return titolo;
	}
	public String getTesto() {
		return testo;
	}
}
