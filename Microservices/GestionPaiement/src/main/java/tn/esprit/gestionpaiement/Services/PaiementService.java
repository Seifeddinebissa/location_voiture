package tn.esprit.gestionpaiement.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gestionpaiement.Entities.Mode;
import tn.esprit.gestionpaiement.Entities.Paiement;
import tn.esprit.gestionpaiement.Repositories.PaiementRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaiementService {
    @Autowired
    private PaiementRepository paiementRepository;

    // Créer un paiement
    public Paiement createPaiement(Double montant, Mode mode) {
        Paiement paiement = new Paiement(montant, mode, false);
        return paiementRepository.save(paiement); }

    // Mettre à jour un paiement
    public Paiement updatePaiement(Long idPaiement, Double montant, Mode mode, boolean traited) {
        Optional<Paiement> paiementOpt = paiementRepository.findById(idPaiement);
        if (paiementOpt.isPresent()) {
            Paiement paiement = paiementOpt.get();
            paiement.setMontant(montant);
            paiement.setMode(mode);
            paiement.setTraited(traited);
            return paiementRepository.save(paiement); }
        throw new RuntimeException("Paiement non trouvé"); }

    // Supprimer un paiement
    public void deletePaiement(Long idPaiement) { paiementRepository.deleteById(idPaiement);}

    // Récupérer tous les paiements
    public List<Paiement> getAllPaiements() {return paiementRepository.findAll();}

    // Récupérer un paiement par ID
    public Paiement getPaiementById(Long idPaiement) {
        return paiementRepository.findById(idPaiement)
                .orElseThrow(() -> new RuntimeException("Paiement non trouvé")); }
    public List<Paiement> getAllPaiement(){ return paiementRepository.findAll();}

    // Fonctionnalité avancée : Paiement en plusieurs fois
    public List<Paiement> paiementEnPlusieursFois(Double montantTotal, int nombreEcheances, Mode mode) {
        List<Paiement> paiements = new ArrayList<>();
        Double montantParEcheance = Double.valueOf(montantTotal / nombreEcheances);
        for (int i = 0; i < nombreEcheances; i++) {
            Paiement paiement = new Paiement(montantParEcheance, mode, false);
            paiements.add(paiementRepository.save(paiement)); }
        return paiements; }

// Fonctionnalité avancée : Remboursement automatique
public Paiement rembourserPaiement(Long idPaiement) {
    Paiement paiement = getPaiementById(idPaiement);
    if (paiement.isTraited()) {
        paiement.setMontant(Double.valueOf(0.0)); // Correction ici
        paiement.setTraited(true);
        return paiementRepository.save(paiement); }
    throw new RuntimeException("Le paiement n'est pas encore traité, remboursement impossible"); }

    // Fonctionnalité avancée : Détection de fraude (exemple simple)
    public boolean detecterFraude(Long idPaiement) {
        Paiement paiement = getPaiementById(idPaiement);
        // Logique simplifiée : montant suspect si > 10000
        return paiement.getMontant() > 10000;}

    // Fonctionnalité avancée : Génération de facture intelligente
    public String genererFacture(Long idPaiement) {
        Paiement paiement = getPaiementById(idPaiement);
        return "Facture #" + paiement.getIdPaiement() + " - Montant: " + paiement.getMontant() +
                " - Mode: " + paiement.getMode() + " - Statut: " + (paiement.isTraited() ? "Traité" : "Non traité");
    }

//    public Paiement getPaiementById(Long idPaiement) {
//        return paiementRepository.findById(idPaiement).get();
//    }
   // public Paiement getPaiementByMontant(Float montant) {return paiementRepository.findByMontant(montant).get();}
   //  public Paiement updatePaiement(  Paiement paiement) {return paiementRepository.save(paiement);}
  //  public void deletePaiement(Long idPaiement) { paiementRepository.deleteById(idPaiement);}
}