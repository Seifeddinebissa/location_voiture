#  Agence de Location de Voitures – Backend Microservices
## 📌 Objectif du Projet

Ce projet a pour but de développer une plateforme complète pour une agence de location de voitures en utilisant une architecture microservices. Chaque service est responsable d’un domaine fonctionnel spécifique et développé par un membre dédié de l’équipe.

##  Technologies
- Backend : Java (sans Spring Boot pour simplifier certains services)
-  Outils : IntelliJ IDEA, Maven
-  Base de données : MySQL / H2
-  Communication : REST API
- 🧪 Tests : Postman
##  Architecture des Microservices

| Service                          | Responsable | Description |
|----------------------------------|-------------|-------------|
| 🚘 Véhicule                     | Dorra       | Gestion du catalogue de voitures |
| 👤 Client                        | Amine       | Gestion des informations clients |
| 📆 Réservation                   | Arij        | Gestion des réservations |
| 💳 Paiement                      | Jamila      | Gestion des paiements |
| 📢 Notification                  | Mohamed     | Envoi de notifications multicanales |
| 🛠 Entretien / Gestion de flotte | Bissa       | Maintenance et suivi des véhicules |
| 🗨 Réclamation                   | Amal        | Gestion des plaintes et support |


 
version 17
version spring 3.4.2
les dependances :
spring web
h2 database
spring data jpa
Rest repositories : manage les repositories
Spring Boot Actuator
Lombok
Mysql driver
