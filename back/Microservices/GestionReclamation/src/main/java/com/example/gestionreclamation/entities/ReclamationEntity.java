package com.example.gestionreclamation.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class ReclamationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private String description;
    private Date dateSubmit;
    private boolean treated;
    private Long reservationId; // New field to link to Reservation

    // ✅ Constructeur sans arguments (obligatoire pour JPA)
    public ReclamationEntity() {
    }

    // ✅ Constructeur avec tous les arguments
    public ReclamationEntity(Long id, String subject, String description, Date dateSubmit, boolean treated) {
        this.id = id;
        this.subject = subject;
        this.description = description;
        this.dateSubmit = dateSubmit;
        this.treated = treated;
    }

    // ✅ Getters
    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateSubmit() {
        return dateSubmit;
    }

    public boolean isTreated() {
        return treated;
    }

    // ✅ Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateSubmit(Date dateSubmit) {
        this.dateSubmit = dateSubmit;
    }

    public void setTreated(boolean treated) {
        this.treated = treated;
    }
}