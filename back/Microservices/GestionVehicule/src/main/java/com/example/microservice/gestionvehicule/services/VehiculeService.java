package com.example.microservice.gestionvehicule.services;

import com.example.microservice.gestionvehicule.dtos.EntretienDto;
import com.example.microservice.gestionvehicule.entities.Vehicule;
import com.example.microservice.gestionvehicule.interfaces.EntretienClient;
import com.example.microservice.gestionvehicule.repository.VehiculeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;




@Service
@Slf4j

public class VehiculeService implements service{

    @Autowired
    private VehiculeRepository vehiculeRepository;
    @Autowired
    private EntretienClient entretienClient;

    // CREATE
    @Override
    public Vehicule createVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    // READ (find all)
    @Override
    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    // READ (find by id)
    @Override
    public Optional<Vehicule> getVehiculeById(Long id) {
        return vehiculeRepository.findById(id);
    }

    // UPDATE
    @Override
    public Vehicule updateVehicule(Long id, Vehicule vehiculeDetails) {
        Optional<Vehicule> existingVehicule = vehiculeRepository.findById(id);
        if (existingVehicule.isPresent()) {
            Vehicule vehicule = existingVehicule.get();
            vehicule.setBrand(vehiculeDetails.getBrand());
            vehicule.setImmatriculation(vehiculeDetails.getImmatriculation());
            vehicule.setDisponibility(vehiculeDetails.getDisponibility());
            return vehiculeRepository.save(vehicule);
        }
        return null; // Or throw an exception if needed
    }

    //
    @Override
    public boolean deleteVehicule(Long id) {
        Optional<Vehicule> vehicule = vehiculeRepository.findById(id);
        if (vehicule.isPresent()) {
            vehiculeRepository.delete(vehicule.get());
            return true;
        }
        return false;
    }

    public List<EntretienDto> getEntretienByVehiculeId(Long id) {
        return entretienClient.findByVehiculeId(id);
    }


}
