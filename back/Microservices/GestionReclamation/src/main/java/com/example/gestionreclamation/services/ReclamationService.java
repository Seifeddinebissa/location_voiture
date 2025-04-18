package com.example.gestionreclamation.services;

import com.example.gestionreclamation.entities.ReclamationEntity;
import com.example.gestionreclamation.repository.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service

public class ReclamationService implements ServiceInterface {

    @Autowired
    private ReclamationRepository reclamationRepository;

    @Override
    public ReclamationEntity addReclamation(ReclamationEntity reclamation) {
        reclamation.setDateSubmit(new Date());   // Définir la date de soumission
        reclamation.setTreated(false);           // Par défaut, une réclamation est non traitée
        return reclamationRepository.save(reclamation); // Enregistrer la réclamation
    }

    @Override
    public List<ReclamationEntity> getAllReclamations() {
        return reclamationRepository.findAll();  // Récupérer toutes les réclamations
    }

    @Override
    public ReclamationEntity getReclamationById(Long id) {
        Optional<ReclamationEntity> reclamation = reclamationRepository.findById(id);
        return reclamation.orElse(null);         // Retourner la réclamation si elle existe ou null sinon
    }

    @Override
    public ReclamationEntity updateReclamation(Long id, ReclamationEntity updatedReclamation) {
        return reclamationRepository.findById(id).map(reclamation -> {
            reclamation.setDescription(updatedReclamation.getDescription());
            reclamation.setTreated(updatedReclamation.isTreated());
            reclamation.setDateSubmit(updatedReclamation.getDateSubmit());
            reclamation.setSubject(updatedReclamation.getSubject());
            return reclamationRepository.save(reclamation);  // Sauvegarder les modifications
        }).orElse(null);  // Retourner null si la réclamation n’existe pas
    }

    @Override
    public void deleteReclamation(Long id) {
        reclamationRepository.deleteById(id);  // Supprimer la réclamation par son ID
    }}
