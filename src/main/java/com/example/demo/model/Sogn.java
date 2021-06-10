package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Sogn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Long kode;

    private String navn;

    //OneToOne relationship
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "kommune_id")
    private Kommune kommune;

    private Double smittetryk;

    private LocalDate datoForNedlukning;

    public Sogn() {
    }

    public Sogn(Long kode, String navn, Kommune kommune, Double smittetryk, LocalDate datoForNedlukning) {
        this.kode = kode;
        this.navn = navn;
        this.kommune = kommune;
        this.smittetryk = smittetryk;
        this.datoForNedlukning = datoForNedlukning;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getKode() {
        return kode;
    }

    public void setKode(Long kode) {
        this.kode = kode;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Kommune getKommune() {
        return kommune;
    }

    public void setKommune(Kommune kommune) {
        this.kommune = kommune;
    }

    public Double getSmittetryk() {
        return smittetryk;
    }

    public void setSmittetryk(Double smittetryk) {
        this.smittetryk = smittetryk;
    }

    public LocalDate getDatoForNedlukning() {
        return datoForNedlukning;
    }

    public void setDatoForNedlukning(LocalDate datoForNedlukning) {
        this.datoForNedlukning = datoForNedlukning;
    }

    @Override
    public String toString() {
        return "Sogn{" +
                "id=" + id +
                ", kode=" + kode +
                ", navn='" + navn + '\'' +
                ", kommune=" + kommune +
                ", smittetryk=" + smittetryk +
                ", datoForNedlukning=" + datoForNedlukning +
                '}';
    }
}