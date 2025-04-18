package com.example.microservice.gestionvehicule.interfaces;

import com.example.microservice.gestionvehicule.dtos.EntretienDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "GestionEntretien")
public interface EntretienClient {
    @RequestMapping("api/entretien/get-by-id-vehicule/{id}")
    public List<EntretienDto> findByVehiculeId(@PathVariable("id") Long id);
}
