package tn.esprit.notification.entities;

import jakarta.persistence.*;
import tn.esprit.notification.enums.TypeNotification;

import java.util.Date;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @Enumerated(EnumType.STRING)

    private TypeNotification typeNotification;

    private Date date;

    public Notification(Long id, String message, TypeNotification typeNotification, Date date) {
        this.id = id;
        this.message = message;
        this.typeNotification = typeNotification;
        this.date = date;
    }

    public Notification() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TypeNotification getTypeNotification() {
        return typeNotification;
    }

    public void setTypeNotification(TypeNotification typeNotification) {
        this.typeNotification = typeNotification;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "notification{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", type='" + typeNotification + '\'' +
                ", date=" + date +
                '}';
    }
}
