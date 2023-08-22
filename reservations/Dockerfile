# Starts with the Java Runtine Base Image.
FROM openjdk:17-jdk-slim as build

# Attach the .jar microservice to container
COPY target/reservations-0.0.1-SNAPSHOT.jar reservations-0.0.1-SNAPSHOT.jar

# Execute microservice
ENTRYPOINT ["java", "-jar", "/reservations-0.0.1-SNAPSHOT.jar"]