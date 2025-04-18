package com.example.microservice.gestionvehicule.dtos;

import com.example.microservice.gestionvehicule.enums.Status;
import com.example.microservice.gestionvehicule.enums.TypeEntretien;

import java.util.Date;

public class EntretienDto {
    private Long id;
    private Date date;
    private TypeEntretien typeEntretien;
    private String description;
    private Status status;
    private Double cost;
}
