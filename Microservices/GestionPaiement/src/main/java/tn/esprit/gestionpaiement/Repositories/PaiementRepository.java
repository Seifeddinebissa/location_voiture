package tn.esprit.gestionpaiement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.gestionpaiement.Entities.Paiement;

import java.util.List;
@Repository
public interface PaiementRepository extends JpaRepository<Paiement,Long> {
    // Méthode personnalisée pour trouver les paiements non traités
    List<Paiement> findByTraitedFalse();
import tn.esprit.gestionpaiement.Entities.Paiement;

import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement,Long> {

 //   List<Paiement> getPaiementById(Long idPaiement);
}
