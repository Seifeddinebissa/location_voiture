FROM openjdk:21
EXPOSE 8888
ADD target/config-server-0.0.1-SNAPSHOT.jar confserver.jar
ENTRYPOINT ["java", "-jar","confserver.jar"]