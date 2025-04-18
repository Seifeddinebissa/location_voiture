package tn.esprit.gestionreservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gestionreservation.entities.Reservation;
import tn.esprit.gestionreservation.repositories.ReservationRepository;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservation(){return reservationRepository.findAll();}
    public Reservation getReservationById(Long id){return reservationRepository.findById(id).get();}
    public  Reservation addReservation(Reservation reservation){return reservationRepository.save(reservation);}
    public Reservation updateReservation(Reservation reservation){return reservationRepository.save(reservation);}
    public void deleteReservation(Long id){reservationRepository.deleteById(id);}
}
