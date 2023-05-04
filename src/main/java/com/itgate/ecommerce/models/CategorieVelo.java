package com.itgate.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class CategorieVelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type ;
    private String description;
    private String image;

    @OneToMany(mappedBy = "categorieVelo",cascade =CascadeType.REMOVE)
    private Collection<Competition> competitions;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
@JsonIgnore
    public Collection<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Collection<Competition> competitions) {
        this.competitions = competitions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
