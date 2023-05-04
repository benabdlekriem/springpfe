package com.itgate.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String date;
    private String lieu ;
    private String map;
    private String distance;
    private String point ;
    private String concretiser;
    private String genre;
    private String etat;
    private String nomcompetition;





    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private CategorieVelo categorieVelo;



    @ManyToMany
    @JoinTable(name="COMP_ARBITRE",joinColumns = @JoinColumn (name ="COMP_ID"),
            inverseJoinColumns =@JoinColumn(name = "ARB_ID"))
    private Collection<Arbitre> arbitres;

    @OneToOne(mappedBy = "competition",cascade =CascadeType.REMOVE)
    private ResultatCompetition resultatCompetition;


    @ManyToMany
    @JoinTable(name="COMP_COUREUR",joinColumns = @JoinColumn (name ="COMP_ID"),
            inverseJoinColumns =@JoinColumn(name = "COUR_ID"))
    private Collection<Coureur> coureurs;


    public void addarbitre(Arbitre a){
        if(arbitres==null){
            arbitres=new ArrayList<>();
        }
        arbitres.add(a);
    }










    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getConcretiser() {
        return concretiser;
    }

    public void setConcretiser(String concretiser) {
        this.concretiser = concretiser;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    public String getNomcompetition() {
        return nomcompetition;
    }

    public void setNomcompetition(String nomcompetition) {
        this.nomcompetition = nomcompetition;
    }



    public CategorieVelo getCategorieVelo() {
        return categorieVelo;
    }

    public void setCategorieVelo(CategorieVelo categorieVelo) {
        this.categorieVelo = categorieVelo;
    }

    public Collection<Arbitre> getArbitres() {
        return arbitres;
    }

    public void setArbitres(Collection<Arbitre> arbitres) {
        this.arbitres = arbitres;
    }



@JsonIgnore
    public ResultatCompetition getResultatCompetition() {
        return resultatCompetition;
    }

    public void setResultatCompetition(ResultatCompetition resultatCompetition) {
        this.resultatCompetition = resultatCompetition;
    }

    public Collection<Coureur> getCoureurs() {
        return coureurs;
    }

    public void setCoureurs(Collection<Coureur> coureurs) {
        this.coureurs = coureurs;
    }

    public void addcoureur(Coureur c){
        if(coureurs==null){
            coureurs=new ArrayList<>();
        }
        coureurs.add(c);
    }
}
