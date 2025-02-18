package tn.esprit.gestionpaiement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gestionpaiement.Entities.Paiement;

import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement,Long> {

 //   List<Paiement> getPaiementById(Long idPaiement);
}
