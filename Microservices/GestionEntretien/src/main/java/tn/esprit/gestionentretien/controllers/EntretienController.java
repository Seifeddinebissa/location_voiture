package tn.esprit.gestionentretien.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestionentretien.entities.Entretien;
import tn.esprit.gestionentretien.services.EntretienService;

import java.util.List;

@RestController
@RequestMapping("entretien")
public class EntretienController {
    @Autowired
    private EntretienService entretienService;

    @Value("${welcome.message:Bienvenue par défaut}")
    private String welcomeMessage;

    @GetMapping("/welcome")
    public String welcome() {
        return welcomeMessage;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Entretien>> getAllEntretien() {
        return new ResponseEntity<>(entretienService.getAllEntretien(),
                HttpStatus.OK);
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<Entretien> getEntretienById(@RequestParam("id") Long id) {
        return new ResponseEntity<>(entretienService.getEntretienById(id),
                HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Entretien> addEntretien(@RequestBody Entretien ent) {
        return new ResponseEntity<>(entretienService.addEntretien(ent), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Entretien> updateEntretien(@RequestBody Entretien ent) {
        return new ResponseEntity<>(entretienService.updateEntretien(ent), HttpStatus.OK);
    }

    @DeleteMapping("/delete-by-id")
    public ResponseEntity deleteEntretien(@RequestParam("id") Long id) {
        entretienService.deleteEntretien(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }
}




//package tn.esprit.gestionentretien.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import tn.esprit.gestionentretien.entities.Entretien;
//import tn.esprit.gestionentretien.services.EntretienService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("entretien")
//public class EntretienController {
//    @Autowired
//    private EntretienService entretienService;
//
//    @Value("${welcome.message}")
//    private String welcomeMessage;
//    @GetMapping("/welcome")
//    public String welcome() {return welcomeMessage;}
//
//    @GetMapping("/get-all")
//    public ResponseEntity<List<Entretien>> getAllEntretien() {
//        return new ResponseEntity<>(entretienService.getAllEntretien(), HttpStatus.OK);
//    }
//    @GetMapping("/get-by-id")
//    public ResponseEntity<Entretien> getEntretienById(@RequestParam("id") Long id) {
//        return new ResponseEntity<>(entretienService.getEntretienById(id), HttpStatus.OK);
//    }
//    @PostMapping("/add")
//    public ResponseEntity<Entretien> addEntretien(@RequestBody Entretien ent) {
//        return new ResponseEntity<>(entretienService.addEntretien(ent), HttpStatus.CREATED);
//    }
//    @PutMapping("/update")
//    public ResponseEntity<Entretien> updateEntretien(@RequestBody Entretien ent) {
//        return new ResponseEntity<>(entretienService.updateEntretien(ent), HttpStatus.OK);
//    }
//    @DeleteMapping("/delete-by-id")
//    public ResponseEntity deleteEntretien(@RequestParam("id") Long id) {
//        entretienService.deleteEntretien(id);
//        return new ResponseEntity<>("Successfully deleted",HttpStatus.OK);
//    }
//}
}
