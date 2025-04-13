package tn.esprit.gestionpaiement.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestionpaiement.Entities.Mode;
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


    private final PaiementService paiementService;

    public PaiementController(PaiementService paiementService) {this.paiementService = paiementService;}
//
//    @Value("${welcome.message:Welcome to Gestion Paiement!}")
//    private String welcomeMessage;
//
//    @GetMapping("/welcome")
//    public String welcome() {
//        return welcomeMessage;
//    }

    @PostMapping("/createPaiement")
    public ResponseEntity<Paiement> createPaiement(@RequestBody Paiement paiement) {
        Paiement saved = paiementService.createPaiement(paiement);
        return ResponseEntity.ok(saved);
    }

    // Mettre à jour un paiement (typo corrected: "updatePatement" -> "updatePaiement")
    @PutMapping("updatePaiement/{id}")
    public ResponseEntity<Paiement> updatePaiement(@PathVariable("id") Long idPaiement,
                                                   @RequestParam Double montant,
                                                   @RequestParam Mode mode,
                                                   @RequestParam boolean traited) {
        return ResponseEntity.ok(paiementService.updatePaiement(idPaiement, montant, mode, traited));
    }

    // Supprimer un paiement
    @DeleteMapping("deletePaiement/{idPaiement}")
    public ResponseEntity<Void> deletePaiement(@PathVariable Long idPaiement) {
        paiementService.deletePaiement(idPaiement);
        return ResponseEntity.noContent().build();
    }

    // Récupérer tous les paiements
    @GetMapping("GetAllPaiements")
    public ResponseEntity<List<Paiement>> getAllPaiements() {
        return ResponseEntity.ok(paiementService.getAllPaiements());
    }

    // Récupérer un paiement par ID
    @GetMapping("getPaiementById/{id}")
    public ResponseEntity<Paiement> getPaiementById(@PathVariable Long idPaiement) {
        return ResponseEntity.ok(paiementService.getPaiementById(idPaiement));
    }

    // Paiement en plusieurs fois
    @PostMapping("/echeances")
    public ResponseEntity<List<Paiement>> paiementEnPlusieursFois(@RequestParam Double montantTotal,
                                                                  @RequestParam int nombreEcheances,
                                                                  @RequestParam Mode mode) {
        return ResponseEntity.ok(paiementService.paiementEnPlusieursFois(montantTotal, nombreEcheances, mode));
    }

    // Remboursement automatique
    @PostMapping("/{id}/rembourser")
    public ResponseEntity<Paiement> rembourserPaiement(@PathVariable Long idPaiement) {
        return ResponseEntity.ok(paiementService.rembourserPaiement(idPaiement));
    }

    // Détection de fraude
    @GetMapping("/{idPaiement}/fraude")
    public ResponseEntity<Boolean> detecterFraude(@PathVariable Long idPaiement) {
        return ResponseEntity.ok(paiementService.detecterFraude(idPaiement));
    }

    // Génération de facture
    @GetMapping("/{idPaiement}/facture")
    public ResponseEntity<String> genererFacture(@PathVariable Long idPaiement) {
        return ResponseEntity.ok(paiementService.genererFacture(idPaiement));
    }
    //@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@GetMapping("/get-all-Paiement")
    public ResponseEntity<List<Paiement>> getAllPaiement() {
        return new ResponseEntity<>(paiementService.getAllPaiement(), HttpStatus.OK);}

//    @GetMapping("/get-by-id")
//    public ResponseEntity<Paiement> getPaiementById(@RequestParam("id") Long idPaiement) {
//        return new ResponseEntity<>(paiementService.getPaiementById(idPaiement), HttpStatus.OK);
//    }

 @PostMapping("/{add-Paiement}")
    public ResponseEntity<Paiement>addPaiement(@RequestBody Paiement paiement) {
    return new ResponseEntity<>(paiementService.addPaiement(paiement), HttpStatus.CREATED);}

 @PutMapping("/update-Paiement")
    public ResponseEntity<Paiement> updatePaiement(@RequestBody Paiement paiement) {
    return new ResponseEntity<>(paiementService.updatePaiement(paiement), HttpStatus.OK); }

// @DeleteMapping("/delete-Paiement")
//    public ResponseEntity deletePaiement(@RequestParam("idPaiement" )Long idPaiement)  {paiementService.deletePaiement(idPaiement);
//    return new ResponseEntity<>("Deleted Successfully " ,HttpStatus.OK); }

}