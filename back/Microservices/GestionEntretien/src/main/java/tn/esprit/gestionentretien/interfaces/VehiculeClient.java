package tn.esprit.gestionentretien.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tn.esprit.gestionentretien.dtos.Vehicule;

@FeignClient(name = "GestionVehicule")
public interface VehiculeClient {
    @RequestMapping("dorra/vehicules/{id}")
    public Vehicule getVehiculeById(@PathVariable Long id);
}
