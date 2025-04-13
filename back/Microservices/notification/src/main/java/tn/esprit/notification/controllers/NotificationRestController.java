package tn.esprit.notification.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.notification.entities.Notification;
import tn.esprit.notification.services.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins = "http://localhost:4200")
public class NotificationRestController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationRestController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable Long id) {
        return notificationService.getNotificationById(id);
    }

    @PostMapping
    public Notification addNotification(@RequestBody Notification notification) {
        return notificationService.addNotification(notification);
    }

    @PutMapping("/{id}")
    public Notification updateNotification(@PathVariable Long id, @RequestBody Notification notification) {
        notification.setId(id);
        return notificationService.updateNotification(notification);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
    }

    @PostMapping("/send")
    public String sendNotification(@RequestBody Notification notification, @RequestParam String userId) {
        notificationService.sendNotification(notification, userId);
        return "Notification envoyée avec succès";
    }

    @PostMapping("/register-user")
    public ResponseEntity<String> registerUser(@RequestParam String userId) {
        System.out.println("Utilisateur enregistré avec userId : " + userId);
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body("{\"message\": \"Utilisateur enregistré avec succès\"}");
    }
}