# Starts with the Java Runtine Base Image.
FROM openjdk:17-jdk-slim as build

# Attach the .jar microservice to container
COPY target/rooms-0.0.1-SNAPSHOT.jar rooms-0.0.1-SNAPSHOT.jar

# Execute microservice
ENTRYPOINT ["java", "-jar", "/rooms-0.0.1-SNAPSHOT.jar"]