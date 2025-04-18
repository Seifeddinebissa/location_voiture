package tn.esprit.gestionentretien.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestionentretien.dtos.Vehicule;
import tn.esprit.gestionentretien.entities.Entretien;
import tn.esprit.gestionentretien.services.EntretienService;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/entretien")
public class EntretienController {
    @Autowired
    private EntretienService entretienService;
    @GetMapping("/get-all")
    public ResponseEntity<List<Entretien>> getAllEntretien() {
        return new ResponseEntity<>(entretienService.getAllEntretien(),
                HttpStatus.OK);
    }
    @GetMapping("/get-by-id")
    public ResponseEntity<Entretien> getEntretienById(@RequestParam("id") Long id) {
        return new ResponseEntity<>(entretienService.getEntretienById(id),
                HttpStatus.OK);
    }
    @PostMapping("/add/{id}")
    public ResponseEntity<Entretien> addEntretien(@RequestBody Entretien ent, @PathVariable Long id) {
        return new ResponseEntity<Entretien>(entretienService.addEntretien(ent,id), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Entretien> updateEntretien(@RequestBody Entretien ent) {
        return new ResponseEntity<>(entretienService.updateEntretien(ent), HttpStatus.OK);
    }
    @DeleteMapping("/delete-by-id")
    public ResponseEntity deleteEntretien(@RequestParam("id") Long id) {
        entretienService.deleteEntretien(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }
    @RequestMapping("dorra/vehicules/{id}")
    public Vehicule getVehiculeById(@PathVariable("id") Long id) {
        return entretienService.getVehiculeById(id);
    }
    @GetMapping("/get-by-id-vehicule")
    public List<Entretien> findByVehiculeId(@RequestParam("id") Long id) {
        return entretienService.findByVehiculeId(id);
    }

}