package com.example.microservice.gestionvehicule.controller;


import com.example.microservice.gestionvehicule.entities.Vehicule;
import com.example.microservice.gestionvehicule.services.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/vehicules")
public class VehiculeRestApi {

    @Autowired
    private VehiculeService vehiculeService;

    @Value("${welcome.message:Bienvenue par défaut}")
    private String welcomeMessage;

    @GetMapping("/welcome")
    public String welcome() {return welcomeMessage;}


    // CREATE
    @PostMapping
    public ResponseEntity<Vehicule> createVehicule(@RequestBody Vehicule vehicule) {
        Vehicule createdVehicule = vehiculeService.createVehicule(vehicule);
        return new ResponseEntity<>(createdVehicule, HttpStatus.CREATED);
    }

    // READ (all)
    @GetMapping
    public ResponseEntity<List<Vehicule>> getAllVehicules() {
        List<Vehicule> vehicules = vehiculeService.getAllVehicules();
        return new ResponseEntity<>(vehicules, HttpStatus.OK);
    }

    // READ (by id)
    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> getVehiculeById(@PathVariable Long id) {
        Optional<Vehicule> vehicule = vehiculeService.getVehiculeById(id);
        return vehicule.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> updateVehicule(@PathVariable Long id, @RequestBody Vehicule vehiculeDetails) {
        Vehicule updatedVehicule = vehiculeService.updateVehicule(id, vehiculeDetails);
        return updatedVehicule != null ? ResponseEntity.ok(updatedVehicule) : ResponseEntity.notFound().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        boolean isDeleted = vehiculeService.deleteVehicule(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
