FROM openjdk:17

EXPOSE 8082
ADD target/keyclock-0.0.1-SNAPSHOT.jar keyclock.jar

ENTRYPOINT ["java", "-jar","keyclock.jar"]