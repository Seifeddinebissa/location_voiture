package tn.esprit.gestionpaiement.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gestionpaiement.Entities.Paiement;
import tn.esprit.gestionpaiement.Repositories.PaiementRepository;

import java.util.List;

@Service
public class PaiementService {
    @Autowired
    private PaiementRepository paiementRepository;


    public List<Paiement> getAllPaiement(){return paiementRepository.findAll();}

    public Paiement getPaiementById(Long idPaiement) {
        return paiementRepository.findById(idPaiement).get();
    }
   // public Paiement getPaiementByMontant(Float montant) {return paiementRepository.findByMontant(montant).get();}
    public Paiement addPaiement(Paiement paiement) {return paiementRepository.save(paiement);}
    public Paiement updatePaiement(  Paiement paiement) {return paiementRepository.save(paiement);}
    public void deletePaiement(Long idPaiement) { paiementRepository.deleteById(idPaiement);}
}