package com.itgate.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Coureur {
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
    private String genre;
    private String image;
    private String categorycoureur;

  private String equipenational;
  private  String description;
    private String enabled;

    private String distance;

    @ManyToMany(mappedBy="coureurs",cascade =CascadeType.REMOVE)
    private Collection<Competition> competitions;


    @ManyToOne
    @JoinColumn(name = "id_equipefeder")
    private EquipeFederation equipeFederation;

    private String classement;
    private String dossards;


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








    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCategorycoureur() {
        return categorycoureur;
    }

    public void setCategorycoureur(String categorycoureur) {
        this.categorycoureur = categorycoureur;
    }

    public String getEquipenational() {
        return equipenational;
    }

    public void setEquipenational(String equipenational) {
        this.equipenational = equipenational;
    }

    public EquipeFederation getEquipeFederation() {
        return equipeFederation;
    }

    public void setEquipeFederation(EquipeFederation equipeFederation) {
        this.equipeFederation = equipeFederation;
    }
@JsonIgnore
    public Collection<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Collection<Competition> competitions) {
        this.competitions = competitions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getClassement() {
        return classement;
    }

    public void setClassement(String classement) {
        this.classement = classement;
    }

    public String getDossards() {
        return dossards;
    }

    public void setDossards(String dossards) {
        this.dossards = dossards;
    }
}
