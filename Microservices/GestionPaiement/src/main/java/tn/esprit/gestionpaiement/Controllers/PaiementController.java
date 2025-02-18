package tn.esprit.gestionpaiement.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestionpaiement.Entities.Paiement;
import tn.esprit.gestionpaiement.Services.PaiementService;

import java.util.List;

@RestController
@RequestMapping("/Gestion_Paiement")
public class PaiementController {

    @Autowired
    private PaiementService paiementService;

    //@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@GetMapping("/get-all-Paiement")
    public ResponseEntity<List<Paiement>> getAllPaiement() {
        return new ResponseEntity<>(paiementService.getAllPaiement(), HttpStatus.OK);}

    @GetMapping("/get-by-id")
    public ResponseEntity<Paiement> getPaiementById(@RequestParam("id") Long idPaiement) {
        return new ResponseEntity<>(paiementService.getPaiementById(idPaiement), HttpStatus.OK);
    }

 @PostMapping("/{add-Paiement}")
    public ResponseEntity<Paiement>addPaiement(@RequestBody Paiement paiement) {
    return new ResponseEntity<>(paiementService.addPaiement(paiement), HttpStatus.CREATED);}

 @PutMapping("/update-Paiement")
    public ResponseEntity<Paiement> updatePaiement(@RequestBody Paiement paiement) {
    return new ResponseEntity<>(paiementService.updatePaiement(paiement), HttpStatus.OK); }

 @DeleteMapping("/delete-Paiement")
    public ResponseEntity deletePaiement(@RequestParam("idPaiement" )Long idPaiement)  {paiementService.deletePaiement(idPaiement);
    return new ResponseEntity<>("Deleted Successfully " ,HttpStatus.OK); }

}