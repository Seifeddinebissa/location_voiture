package com.example.gestionreclamation.controller;


import com.example.gestionreclamation.entities.ReclamationEntity;
import com.example.gestionreclamation.services.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Reclamation")
public class ReclamationRestAPI {
    @Autowired
    private ReclamationService reclamationService;
    // Ajouter une nouvelle réclamation

    @PostMapping("/add")
    public ReclamationEntity addReclamation(@RequestBody ReclamationEntity reclamation) {
        return reclamationService.addReclamation(reclamation);  // ✅ Ajoute via endpoint
    }
    // ✅ Récupérer toutes les réclamations
    @GetMapping("/all")
    public ResponseEntity<List<ReclamationEntity>> getAllReclamations() {
        List<ReclamationEntity> reclamations = reclamationService.getAllReclamations();
        return ResponseEntity.ok(reclamations); // Retourne la liste des réclamations
    }

    // ✅ Récupérer une réclamation par son ID
    @GetMapping("/{id}")
    public ResponseEntity<ReclamationEntity> getReclamationById(@PathVariable Long id) {
        ReclamationEntity reclamation = reclamationService.getReclamationById(id);
        return (reclamation != null) ? ResponseEntity.ok(reclamation) : ResponseEntity.notFound().build();
    }

    // ✅ Mettre à jour une réclamation existante
    @PutMapping("/update/{id}")
    public ResponseEntity<ReclamationEntity> updateReclamation(@PathVariable Long id, @RequestBody ReclamationEntity updatedReclamation) {
        ReclamationEntity updated = reclamationService.updateReclamation(id, updatedReclamation);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // ✅ Supprimer une réclamation par son ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReclamation(@PathVariable Long id) {
        reclamationService.deleteReclamation(id);
        return ResponseEntity.noContent().build(); // Retourne 204 No Content après suppression
    }
}