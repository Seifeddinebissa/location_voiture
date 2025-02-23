package tn.esprit.GestionVoiture.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.GestionVoiture.entities.Vehicule;
import tn.esprit.GestionVoiture.services.VehiculeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/vehicules")
public class VehiculeController {
    private final VehiculeService service;

    public VehiculeController(VehiculeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Vehicule> getAllVehicules() {
        return service.getAllVehicules();
    }

    @GetMapping("/{id}")
    public Vehicule getVehiculeById(@PathVariable Long id) {
        return service.getVehiculeById(id);
    }

    @PostMapping("/vehicules")
    public ResponseEntity<Vehicule> saveVehicule(@RequestBody Vehicule vehicule) {
        System.out.println("Received Vehicule: " + vehicule);
        Vehicule savedVehicule = service.saveVehicule(vehicule);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicule);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicule(@PathVariable Long id) {
        service.deleteVehicule(id);
    }
}