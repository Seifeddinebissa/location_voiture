# Étape 1 : Utilisation d'une image de base avec Java 17
FROM openjdk:17-jdk-slim

# Étape 2 : Exposition du port sur lequel l'application Spring Boot va tourner
EXPOSE 8082

# Étape 3 : Ajout du fichier .jar généré dans l'image Docker
ADD target/GestionVehicule-0.0.1-SNAPSHOT.jar app.jar

# Étape 4 : Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "/app.jar"]