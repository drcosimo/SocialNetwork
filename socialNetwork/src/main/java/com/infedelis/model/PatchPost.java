package com.infedelis.model;

public class PatchPost {
	private Integer id;
	private String nickname;
	private String password;
	private String titolo;
	private String testo;
	public PatchPost(Integer id, String nickname, String password, String titolo, String testo) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.password = password;
		this.titolo = titolo;
		this.testo = testo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "PatchPost [id=" + id + ", nickname=" + nickname + ", password=" + password + ", titolo=" + titolo
				+ ", testo=" + testo + "]";
	}
	
}
