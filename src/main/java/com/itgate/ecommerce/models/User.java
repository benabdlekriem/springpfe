package com.itgate.ecommerce.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// confiramtion de l'email
	private Boolean confirm;
	// password ken nsineh
	private String passwordResetToken;

	@NotBlank
	@Size(max = 20)
	private String username;


	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
private String adress;
	private String cin;
	private String numlicence;
	private String tel;
	private String etat;

	@NotBlank
	@Size(max = 120)
	private String password;
	private  String image;
	private  String nom;
	private  String prenom;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();



	@OneToMany(mappedBy = "user",cascade =CascadeType.REMOVE)
	private Collection<EquipeFederation> equipeFederations;

	public User() {
	}

	public User(String username, String email, String password,String adress, String cin, String numlicence, String tel, String etat,String nom,String prenom) {
		this.username = username;
		this.email = email;
		this.adress = adress;
		this.cin = cin;
		this.numlicence = numlicence;
		this.tel = tel;
		this.etat = etat;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getConfirm() {
		return confirm;
	}

	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}

	public String getPasswordResetToken() {
		return passwordResetToken;
	}

	public void setPasswordResetToken(String passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}
@JsonIgnore
	public Collection<EquipeFederation> getEquipeFederations() {
		return equipeFederations;
	}

	public void setEquipeFederations(Collection<EquipeFederation> equipeFederations) {
		this.equipeFederations = equipeFederations;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getNumlicence() {
		return numlicence;
	}

	public void setNumlicence(String numlicence) {
		this.numlicence = numlicence;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}
