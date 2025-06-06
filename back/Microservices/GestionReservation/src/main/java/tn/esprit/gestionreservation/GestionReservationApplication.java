package tn.esprit.gestionreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GestionReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionReservationApplication.class, args);
    }

}
