package com.example.gestionreclamation.services;

import com.example.gestionreclamation.entities.ReclamationEntity;

import java.util.List;

public interface ServiceInterface {
    ReclamationEntity addReclamation(ReclamationEntity reclamation);     // Créer une réclamation
    List<ReclamationEntity> getAllReclamations();                     // Lire toutes les réclamations

    ReclamationEntity getReclamationById(Long id);                    // Lire une réclamation par ID

    ReclamationEntity updateReclamation(Long id, ReclamationEntity reclamation); // Mettre à jour une réclamation

    void deleteReclamation(Long id);                                  // Supprimer une réclamation
}

