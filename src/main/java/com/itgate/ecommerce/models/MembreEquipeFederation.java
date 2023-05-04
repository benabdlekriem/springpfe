package com.itgate.ecommerce.models;

import jakarta.persistence.*;

@Entity
public class MembreEquipeFederation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numlicence;
    private String nom;
    private String prenom;
    private String tel;
    private String email;
    private String cin;
    private String region ;
    private String datenaissance;
    private String fonction;
    private String image;
    @ManyToOne
    @JoinColumn(name = "id_equipefederation")
    private EquipeFederation  equipeFederation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumlicence() {
        return numlicence;
    }

    public void setNumlicence(String numlicence) {
        this.numlicence = numlicence;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(String datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public EquipeFederation getEquipeFederation() {
        return equipeFederation;
    }

    public void setEquipeFederation(EquipeFederation equipeFederation) {
        this.equipeFederation = equipeFederation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
