package tn.esprit.GestionVoiture.repositorys;

import tn.esprit.GestionVoiture.entities.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
}
