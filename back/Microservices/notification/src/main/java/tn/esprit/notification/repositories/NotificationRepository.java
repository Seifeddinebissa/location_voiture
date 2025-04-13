package tn.esprit.notification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.notification.entities.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
