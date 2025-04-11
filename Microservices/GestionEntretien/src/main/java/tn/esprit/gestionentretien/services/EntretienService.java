package tn.esprit.gestionentretien.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gestionentretien.entities.Entretien;
import tn.esprit.gestionentretien.repositorys.EntretienRepository;

import java.util.List;

@Service
public class EntretienService {
    @Autowired
    private final EntretienRepository entretienRepository;
    public EntretienService(EntretienRepository entretienRepository) {this.entretienRepository = entretienRepository;}
    public List<Entretien> getAllEntretien() {
        return entretienRepository.findAll();
    }
    public Entretien getEntretienById(Long id) {
        return entretienRepository.findById(id).get();
    }
    public Entretien addEntretien(Entretien ent) {
        return entretienRepository.save(ent);
    }
    public Entretien updateEntretien(Entretien ent) {
        return entretienRepository.save(ent);
    }
    public void deleteEntretien(Long id) {
        entretienRepository.deleteById(id);
    }
}
