package tn.esprit.gestionreservation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestionreservation.entities.Reservation;
import tn.esprit.gestionreservation.services.ReservationService;

import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @GetMapping("/get-all")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return new ResponseEntity<>(reservationService.getAllReservation(), HttpStatus.OK);
    }
    @GetMapping("/get-by-id")
    public ResponseEntity<Reservation> getReservationById(@RequestParam("id") Long id) {
        return new ResponseEntity<>(reservationService.getReservationById(id), HttpStatus.OK);

    }
    @PostMapping("/add")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        return new ResponseEntity<>(reservationService.addReservation(reservation), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation) {
        return new ResponseEntity<>(reservationService.updateReservation(reservation), HttpStatus.OK);
    }
    @DeleteMapping("/delete-by-id")
    public ResponseEntity deleteReservationById(@RequestParam("id") Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>("Reservation deleted", HttpStatus.OK);
    }
}
