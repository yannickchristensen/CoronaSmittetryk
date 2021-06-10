package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Kommune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int kommuneKode;
    private String navn;

    @JsonBackReference
    @OneToOne
    private Sogn sogn;

    public Kommune() {
    }

    public Kommune(int kommuneKode, String navn) {
        this.kommuneKode = kommuneKode;
        this.navn = navn;
    }

    public Kommune(int kommuneKode, String navn, Sogn sogn) {
        this.kommuneKode = kommuneKode;
        this.navn = navn;
        this.sogn = sogn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sogn getSogn() {
        return sogn;
    }

    public void setSogn(Sogn sogn) {
        this.sogn = sogn;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getKommuneKode() {
        return kommuneKode;
    }

    public void setKommuneKode(int kommuneKode) {
        this.kommuneKode = kommuneKode;
    }
}
