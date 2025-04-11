package tn.esprit.gestionentretien.entities;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.gestionentretien.enums.Status;
import tn.esprit.gestionentretien.enums.TypeEntretien;
import java.util.Date;

@Entity
public class Entretien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    @Enumerated(EnumType.STRING)
    private TypeEntretien typeEntretien;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Double cost;

    public Entretien() {
    }

    public Entretien(Date date, TypeEntretien typeEntretien, String description, Status status, Double cost) {
        this.date = date;
        this.typeEntretien = typeEntretien;
        this.description = description;
        this.status = status;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TypeEntretien getTypeEntretien() {
        return typeEntretien;
    }

    public void setTypeEntretien(TypeEntretien typeEntretien) {
        this.typeEntretien = typeEntretien;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
