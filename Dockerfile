# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Install MySQL client
RUN apt-get update && apt-get install -y default-mysql-client && rm -rf /var/lib/apt/lists/*

# Copy the entrypoint script
COPY entrypoint.sh .

# Make the entrypoint script executable
RUN chmod +x entrypoint.sh

# Expose the application port
EXPOSE 8081

# Copy the JAR file into the container
COPY target/flightreservation-0.0.1-SNAPSHOT.jar /app/flightreservation.jar

# Define the entrypoint and command
ENTRYPOINT ["./entrypoint.sh"]
CMD ["java", "-jar", "flightreservation.jar"]