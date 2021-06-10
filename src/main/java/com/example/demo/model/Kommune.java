package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Kommune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Long kode;

    private String navn;

    //OneToOne relationship
    @JsonManagedReference
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "kommune")
    private Set<Sogn> sogne;

    public Kommune() {
    }

    public Kommune(Long kode, String navn) {
        this.kode = kode;
        this.navn = navn;
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

    public Set<Sogn> getSogne() {
        return sogne;
    }

    public void setSogne(Set<Sogn> sogns) {
        this.sogne = sogns;
    }

    @Override
    public String toString() {
        return "Kommune{" +
                "id=" + id +
                ", kode=" + kode +
                ", navn='" + navn + '\'' +
                ", sogns=" + sogne +
                '}';
    }
}