package com.itgate.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class EquipeFederation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String rne;
    private String domination;
    private String datecreation;
    private String dateelection;

    private String image;

    private String enabled;

    private String type;
private String description;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User  user;

    @OneToMany(mappedBy = "equipeFederation",cascade =CascadeType.REMOVE)
    private Collection<Coureur> coureurs;
    @OneToMany(mappedBy = "equipeFederation",cascade =CascadeType.REMOVE)
    private Collection<MembreEquipeFederation> membreEquipeFederations;



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRne() {
        return rne;
    }

    public void setRne(String rne) {
        this.rne = rne;
    }

    public String getDomination() {
        return domination;
    }

    public void setDomination(String domination) {
        this.domination = domination;
    }

    public String getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(String datecreation) {
        this.datecreation = datecreation;
    }

    public String getDateelection() {
        return dateelection;
    }

    public void setDateelection(String dateelection) {
        this.dateelection = dateelection;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
@JsonIgnore
    public Collection<MembreEquipeFederation> getMembreEquipeFederations() {
        return membreEquipeFederations;
    }

    public void setMembreEquipeFederations(Collection<MembreEquipeFederation> membreEquipeFederations) {
        this.membreEquipeFederations = membreEquipeFederations;
    }
@JsonIgnore
    public Collection<Coureur> getCoureurs() {
        return coureurs;
    }

    public void setCoureurs(Collection<Coureur> coureurs) {
        this.coureurs = coureurs;
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
}
