package tn.esprit.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("keyclock",r->r.path("/users/**")
                        .uri("lb://keyclock"))
                .route("entretien",r->r.path("/api/**")
                        .uri("lb://GestionEntretien"))
                .route("paiement",r->r.path("/Gestion_Paiement/**")
                        .uri("lb://GestionPaiement"))
                .route("reclamation",r->r.path("/Reclamation/**")
                        .uri("lb://GestionReclamation"))
                .route("vehicules",r->r.path("/dorra/vehicules/**")
                        .uri("lb://GestionVehicule"))
                .route("reservation",r->r.path("/reservation/**")
                        .uri("lb://GestionReservation"))
                .route("notification",r->r.path("/notifications/**")
                        .uri("lb://notification"))
                .route("openainodejs",r->r.path("/api/**")
                        .uri("lb://OPENAI-SERVICE"))
                .build();
    }
}
