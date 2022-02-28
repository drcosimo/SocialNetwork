package com.infedelis.model;

import java.util.List;
import java.util.Objects;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import javax.persistence.JoinColumn;


@Entity
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String nickname;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String cognome;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private Date dataNascita;
	@Column(nullable = false)
	private Boolean attivo;
	@Column(nullable = false)
	private Boolean isAdmin;
	
	// utente pubblica uno o piu post
	@OneToMany(mappedBy = "utente")
	private List<Post> posts = null;
	
	// like
	@ManyToMany
	@JoinTable(
			name = "MiPiace",
			joinColumns = @JoinColumn(name = "utente_id"),
			inverseJoinColumns = @JoinColumn(name = "post_id")
			)
	private List<Post> liked = null;
	
	// dislike
	@ManyToMany
	@JoinTable(
			name = "NonMiPiace",
			joinColumns = @JoinColumn(name = "utente_id"),
			inverseJoinColumns = @JoinColumn(name = "post_id")
			)
	private List<Post> disliked = null;
	
	
	public Utente() {
		
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Date getDataNascita() {
		return dataNascita;
	}


	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}


	public Boolean getAttivo() {
		return attivo;
	}


	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}


	public Boolean getIsAdmin() {
		return isAdmin;
	}


	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	public List<Post> getPosts() {
		return posts;
	}


	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}


	public List<Post> getLiked() {
		return liked;
	}


	public void setLiked(List<Post> liked) {
		this.liked = liked;
	}


	public List<Post> getDisliked() {
		return disliked;
	}


	public void setDisliked(List<Post> disliked) {
		this.disliked = disliked;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		return Objects.equals(id, other.id);
	}
}
