package tn.esprit.gestionentretien.dtos;


public class Vehicule {
    private Long id;
    private String brand ;
    private String immatriculation ;
    private Boolean disponibility ;

    public Vehicule() {
    }

    public Vehicule(Long id, Boolean disponibility, String immatriculation, String brand) {
        this.id = id;
        this.disponibility = disponibility;
        this.immatriculation = immatriculation;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public Boolean getDisponibility() {
        return disponibility;
    }

    public void setDisponibility(Boolean disponibility) {
        this.disponibility = disponibility;
    }
}
