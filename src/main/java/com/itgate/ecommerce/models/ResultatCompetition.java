package com.itgate.ecommerce.models;

import jakarta.persistence.*;

@Entity
public class ResultatCompetition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




    @OneToOne
    @JoinColumn(name = "id_competition")
    private Competition competition;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }





    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

}
