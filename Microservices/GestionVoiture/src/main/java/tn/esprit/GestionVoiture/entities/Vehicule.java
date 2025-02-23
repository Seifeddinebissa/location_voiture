package tn.esprit.GestionVoiture.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String immatriculation;
    private boolean disponibility;

//    @ManyToOne
//    private Client client;
}
