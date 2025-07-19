# Use a lightweight Java runtime as base image
FROM openjdk:21-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the jar file from target/ to container's /app
COPY target/*.jar app.jar


# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]

