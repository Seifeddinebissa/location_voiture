package tn.esprit.GestionVoiture.services;

import tn.esprit.GestionVoiture.entities.Vehicule;
import tn.esprit.GestionVoiture.repositorys.VehiculeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehiculeService {
    private final VehiculeRepository repository;

    public VehiculeService(VehiculeRepository repository) {
        this.repository = repository;
    }

    public List<Vehicule> getAllVehicules() {
        return repository.findAll();
    }

    public Vehicule getVehiculeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Vehicule saveVehicule(Vehicule vehicule) {
        return repository.save(vehicule);
    }

    public void deleteVehicule(Long id) {
        repository.deleteById(id);
    }
}