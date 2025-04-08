package com.example.microservice.gestionvehicule.repository;

import com.example.microservice.gestionvehicule.entities.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculeRepository  extends JpaRepository<Vehicule,Long> {
}
