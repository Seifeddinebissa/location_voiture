package tn.esprit.gestionpaiement.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor


public class Paiement {
    @Id
    @GeneratedValue
    private Long idPaiement;
    private  Double montant;
    private  Mode mode;
    private  boolean traited;

    public Paiement() {}
    public Paiement(Double montant, Mode mode, boolean traited) {
        this.idPaiement=idPaiement; this.montant = montant;this.mode = mode;
        this.traited = traited;}

    public Long getIdPaiement() {return idPaiement;}

    public void setIdPaiement(Long idPaiement) {this.idPaiement = idPaiement;}

    public Double getMontant() {return montant;}

    public void setMontant(Double montant) {this.montant = montant;}

    public Mode getMode() {return mode;}

    public void setMode(Mode mode) {this.mode = mode;}

    public boolean isTraited() {return traited;}

    public void setTraited(boolean traited) {this.traited = traited;}

}
