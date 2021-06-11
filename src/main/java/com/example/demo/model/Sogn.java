package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Sogn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Long kode;
    private String navn;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "kommune_id")
    private Kommune kommune;
    private Double smittetryk;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datoForNedlukning;
    private boolean nedlukket;

    public Sogn() {
    }

    public Sogn(Long kode, String navn, Kommune kommune, Double smittetryk, LocalDate datoForNedlukning, boolean nedlukket) {
        this.kode = kode;
        this.navn = navn;
        this.kommune = kommune;
        this.smittetryk = smittetryk;
        this.datoForNedlukning = datoForNedlukning;
        this.nedlukket = nedlukket;
    }

    public boolean isNedlukket() {
        return nedlukket;
    }

    public void setNedlukket(boolean nedlukket) {
        this.nedlukket = nedlukket;
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

}