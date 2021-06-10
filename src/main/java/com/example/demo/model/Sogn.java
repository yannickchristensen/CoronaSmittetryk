package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Sogn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int sognKode;
    private String navn;
    private double smittetryk;
    private LocalDate nedlukningStart;

    @JsonManagedReference
    @OneToOne (cascade = CascadeType.ALL)
    private Kommune kommune;



    public Sogn(int sognKode, String navn, double smittetryk, LocalDate nedlukningStart) {
        this.sognKode = sognKode;
        this.navn = navn;
        this.smittetryk = smittetryk;
        this.nedlukningStart = nedlukningStart;
    }

    public Sogn() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSognKode() {
        return sognKode;
    }

    public void setSognKode(int sognKode) {
        this.sognKode = sognKode;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public double getSmittetryk() {
        return smittetryk;
    }

    public void setSmittetryk(double smittetryk) {
        this.smittetryk = smittetryk;
    }

    public LocalDate getNedlukningStart() {
        return nedlukningStart;
    }

    public void setNedlukningStart(LocalDate nedlukningStart) {
        this.nedlukningStart = nedlukningStart;
    }

    public Kommune getKommune() {
        return kommune;
    }

    public void setKommune(Kommune kommune) {
        this.kommune = kommune;
    }
}
