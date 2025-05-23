package tn.esprit.gestionentretien.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.gestionentretien.entities.Entretien;

import java.util.List;

@Repository
public interface EntretienRepository extends JpaRepository<Entretien, Long> {
    List<Entretien> findByVehiculeId(Long vehiculeId);
}
