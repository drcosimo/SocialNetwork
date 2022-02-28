package com.infedelis.model;

public class PostDTO {
	
	private Integer id;  //id del post
	private String nickname; //rif utente
	private String password; //dell'utente
	public PostDTO(Integer id, String nickname, String password) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.password = password;
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
	
	
}
