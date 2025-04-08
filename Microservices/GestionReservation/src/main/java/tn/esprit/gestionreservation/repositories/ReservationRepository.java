package tn.esprit.gestionreservation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.gestionreservation.entities.Reservation;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
