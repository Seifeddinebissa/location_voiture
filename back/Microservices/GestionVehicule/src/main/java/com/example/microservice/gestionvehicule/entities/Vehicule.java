package com.example.microservice.gestionvehicule.entities;

import com.example.microservice.gestionvehicule.dtos.EntretienDto;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand ;
    private String immatriculation ;
    private Boolean disponibility ;


    public Vehicule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public Boolean getDisponibility() {
        return disponibility;
    }

    public void setDisponibility(Boolean disponibility) {
        this.disponibility = disponibility;
    }

    public Vehicule(Long id, String brand, String immatriculation, Boolean disponibility) {
        this.id = id;
        this.brand = brand;
        this.immatriculation = immatriculation;
        this.disponibility = disponibility;
    }
}
