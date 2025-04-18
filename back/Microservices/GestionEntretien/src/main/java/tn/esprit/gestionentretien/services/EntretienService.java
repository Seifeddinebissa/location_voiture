package tn.esprit.gestionentretien.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gestionentretien.dtos.Vehicule;
import tn.esprit.gestionentretien.entities.Entretien;
import tn.esprit.gestionentretien.interfaces.VehiculeClient;
import tn.esprit.gestionentretien.repositorys.EntretienRepository;

import java.util.List;

@Service
public class EntretienService {
    @Autowired
    private EntretienRepository entretienRepository;
    @Autowired
    private VehiculeClient vehiculeClientService;

    public Vehicule getVehiculeById(Long id) {
        return vehiculeClientService.getVehiculeById(id);
    }

    public List<Entretien> getAllEntretien() {
        return entretienRepository.findAll();
    }

    public Entretien getEntretienById(Long id) {
        return entretienRepository.findById(id).get();
    }
    public List<Entretien> findByVehiculeId(Long id) {
        return entretienRepository.findByVehiculeId(id);
    }

    public Entretien addEntretien(Entretien ent,Long vehiculeId) {
        Vehicule vehicule = getVehiculeById(vehiculeId);
        if (vehicule != null) {
            ent.setVehiculeId(vehiculeId);
            return entretienRepository.save(ent);
        }else {
            return null;
        }


    }
    public Entretien updateEntretien(Entretien ent) {
        return entretienRepository.save(ent);
    }
    public void deleteEntretien(Long id) {
        entretienRepository.deleteById(id);
    }
}
