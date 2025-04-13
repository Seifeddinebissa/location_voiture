package tn.esprit.notification.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tn.esprit.notification.entities.Notification;
import tn.esprit.notification.enums.TypeNotification;
import tn.esprit.notification.repositories.NotificationRepository;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${onesignal.app-id}")
    private String oneSignalAppId;

    @Value("${onesignal.api-key}")
    private String oneSignalApiKey;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    public Notification addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Notification updateNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    public void sendNotification(Notification notification, String userId) {
        if (notification.getTypeNotification() == TypeNotification.WEB_PUSH) {
            sendWebPushNotification(userId, notification.getMessage());
        } else if (notification.getTypeNotification() == TypeNotification.EMAIL) {
            System.out.println("Envoi EMAIL : " + notification.getMessage());
        } else if (notification.getTypeNotification() == TypeNotification.SMS) {
            System.out.println("Envoi SMS : " + notification.getMessage());
        }
        notificationRepository.save(notification);
    }

    private void sendWebPushNotification(String userId, String message) {
        try {
            String json = objectMapper.writeValueAsString(new Object() {
                public final String app_id = oneSignalAppId;
                public final String[] include_external_user_ids = new String[]{userId};
                public final Object contents = new Object() {
                    public final String en = message;
                };
                public final Object headings = new Object() {
                    public final String en = "Location Voiture";
                };
            });

            System.out.println("Clé API utilisée : " + oneSignalApiKey);
            System.out.println("Requête JSON envoyée à OneSignal : " + json);

            RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));
            Request request = new Request.Builder()
                    .url("https://onesignal.com/api/v1/notifications")
                    .header("Authorization", "Basic " + oneSignalApiKey)
                    .post(body)
                    .build();

            try (Response response = httpClient.newCall(request).execute()) {
                System.out.println("OneSignal Response: " + response.body().string());
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'envoi à OneSignal : " + e.getMessage());
            e.printStackTrace();
        }
    }
}