package com.example.microservice.gestionvehicule.services;

import com.example.microservice.gestionvehicule.entities.Vehicule;

import java.util.List;
import java.util.Optional;

public interface service {

    public Vehicule createVehicule(Vehicule vehicule) ;
    public List<Vehicule> getAllVehicules() ;
    public Optional<Vehicule> getVehiculeById(Long id) ;
    public Vehicule updateVehicule(Long id, Vehicule vehiculeDetails) ;
    public boolean deleteVehicule(Long id) ;



}
