package tn.esprit.gestionentretien.entities;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.gestionentretien.enums.Status;
import tn.esprit.gestionentretien.enums.TypeEntretien;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Entretien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    @Enumerated(EnumType.STRING)
    private TypeEntretien typeEntretien;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Double cost;
}
