package com.infedelis.model;

import java.time.LocalDateTime;

public class PostInRange {
	private String nickname;
	private String password;
	private LocalDateTime dataInizio;
	private LocalDateTime dataFine;
	public PostInRange(String nickname, String password, LocalDateTime dataInizio, LocalDateTime dataFine) {
		super();
		this.nickname = nickname;
		this.password = password;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
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
	public LocalDateTime getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(LocalDateTime dataInizio) {
		this.dataInizio = dataInizio;
	}
	public LocalDateTime getDataFine() {
		return dataFine;
	}
	public void setDataFine(LocalDateTime dataFine) {
		this.dataFine = dataFine;
	}
		
	
}
