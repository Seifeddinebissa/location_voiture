package tn.esprit.gestionreservation.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestionreservation.entities.Reservation;
import tn.esprit.gestionreservation.services.ReservationService;
import java.util.List;

@RestController
@RequestMapping("/GestionReservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/add")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.addReservation(reservation));}




    @GetMapping("/get-all")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservation());}

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));}

    @Value("${welcome.message:Welcome to Gestion Reservation!}")
    private String welcomeMessage;

    @GetMapping("/welcome")
    public String welcome() { return welcomeMessage;}

    @PutMapping("/update")
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.updateReservation(reservation));}

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<String> deleteReservationById(@PathVariable("id") Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok("Reservation deleted successfully"); }
}