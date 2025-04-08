package tn.esprit.gestionpaiement.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestionpaiement.Entities.Mode;
import tn.esprit.gestionpaiement.Entities.Paiement;
import tn.esprit.gestionpaiement.Services.PaiementService;

import java.util.List;

@RestController
@RequestMapping("/Gestion_Paiement")
public class PaiementController {

    @Autowired
    private PaiementService paiementService;

    // Créer un paiement
    @PostMapping("createPaiement")
    public ResponseEntity<Paiement> createPaiement(@RequestParam Double montant, @RequestParam Mode mode) {
        return ResponseEntity.ok(paiementService.createPaiement(montant, mode));}


    // Mettre à jour un paiement
    @PutMapping("updatePatement/{id}")
    public ResponseEntity<Paiement> updatePaiement(@PathVariable Long idPaiement,
                                                        @RequestParam Double montant,
                                                        @RequestParam Mode mode,
                                                        @RequestParam boolean traited) {
        return ResponseEntity.ok(paiementService.updatePaiement(idPaiement, montant, mode, traited)); }

    // Supprimer un paiement
    @DeleteMapping("deletePaiement/{idPaiement}")
    public ResponseEntity<Void> deletePaiement(@PathVariable Long idPaiement) {
        paiementService.deletePaiement(idPaiement);
        return ResponseEntity.noContent().build();}

    // Récupérer tous les paiements
    @GetMapping("GetAllPaiements")
    public ResponseEntity<List<Paiement>> getAllPaiements() {
        return ResponseEntity.ok(paiementService.getAllPaiements());}

    // Récupérer un paiement par ID
    @GetMapping("getPaiementById/{id}")
    public ResponseEntity<Paiement> getPaiementById(@PathVariable Long idPaiement) {
        return ResponseEntity.ok(paiementService.getPaiementById(idPaiement)); }

    // Paiement en plusieurs fois
    @PostMapping("/echeances")
    public ResponseEntity<List<Paiement>> paiementEnPlusieursFois(@RequestParam Double montantTotal,
                                                                  @RequestParam int nombreEcheances,
                                                                  @RequestParam Mode mode) {
        return ResponseEntity.ok(paiementService.paiementEnPlusieursFois(montantTotal, nombreEcheances, mode)); }

    // Remboursement automatique
    @PostMapping("/{id}/rembourser")
    public ResponseEntity<Paiement> rembourserPaiement(@PathVariable Long idPaiement) {
        return ResponseEntity.ok(paiementService.rembourserPaiement(idPaiement));}

    // Détection de fraude
    @GetMapping("/{idPaiement}/fraude")
    public ResponseEntity<Boolean> detecterFraude(@PathVariable Long idPaiement) {
        return ResponseEntity.ok(paiementService.detecterFraude(idPaiement));}

    // Génération de facture
    @GetMapping("/{idPaiement}/facture")
    public ResponseEntity<String> genererFacture(@PathVariable Long idPaiement) {
        return ResponseEntity.ok(paiementService.genererFacture(idPaiement));}

}


