# Flight Reservation System

This is a Flight Reservation System built with Spring Boot, Thymeleaf, and Docker. The application allows users to search for flights, complete reservations, and check-in for flights.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Running the Application](#running-the-application)
  - [Running Locally](#running-locally)
  - [Running in Docker](#running-in-docker)
- [Spring Profiles](#spring-profiles)
- [Environment Variables](#environment-variables)
- [Configuration Files](#configuration-files)
- [Endpoints](#endpoints)
- [HTTP and HTTPS Ports](#http-and-https-ports)
- [License](#license)

## Prerequisites

- Docker
- Docker Compose
- Java 21
- Maven
- MySQL (for running locally)

## Setup

1. Clone the repository:

    ```sh
    git clone https://github.com/kujinlee/flightreservation-springboot.git
    cd flightreservation-springboot
    ```

2. Configure the `.env` file for local development or Docker:

    ### Local Development
    ```properties
    # Environment variables for local development
    MYSQL_ROOT_PASSWORD=<your password>
    MYSQL_DATABASE=reservation
    SPRING_DATASOURCE_USERNAME=root
    SPRING_DATASOURCE_PASSWORD=<your password>
    MYSQL_HOST=localhost
    SPRING_PROFILES_ACTIVE=dev
    APP_PORT=8443
    HTTP_PORT=8080
    KEY_STORE_PASSWORD=<your keystore password>
    KEY_STORE_PATH=certs/keystore.p12
    ```

    ### Dockerized Environment
    The `docker-compose.yml` file automatically sets the following environment variables for Dockerized environments:
    ```yaml
      APP_PORT: ${APP_PORT}
      HTTP_PORT: ${HTTP_PORT}
      MYSQL_HOST: db
      SPRING_DATASOURCE_USERNAME: ${SPRING_DATASOURCE_USERNAME}
      SPRING_DATASOURCE_PASSWORD: ${SPRING_DATASOURCE_PASSWORD}
      SPRING_PROFILES_ACTIVE: ${SPRING_PROFILES_ACTIVE}
      KEY_STORE_PATH: /app/certs/keystore.p12
      KEY_STORE_PASSWORD: ${KEY_STORE_PASSWORD}
    ```

3. Ensure MySQL is running locally if you plan to run the application on localhost.

## Running the Application

### Running Locally

1. Ensure MySQL is running on your local machine and the `reservation` database exists:
    ```sql
    CREATE DATABASE reservation;
    ```

2. Set the required environment variables:
    ```bash
    export SPRING_DATASOURCE_USERNAME=root
    export SPRING_DATASOURCE_PASSWORD=<your password>
    export KEY_STORE_PASSWORD=<your keystore password>
    export KEY_STORE_PATH=certs/keystore.p12
    ```

3. Build and run the application:
    ```sh
    mvn clean install
    ./run.sh
    ```

4. Access the application at:
    ```
    https://localhost:8443/flightreservation-springboot
    ```

### Running in Docker

1. Build the Docker images and start the containers:
    ```sh
    docker-compose up --build
    ```

2. Access the application at:
    ```
    https://localhost:8443/flightreservation-springboot
    ```

3. Stop the containers when done:
    ```sh
    docker-compose down
    ```

## Spring Profiles

Spring Profiles are used to manage environment-specific configurations. This project uses the following profiles:

- **`dev`**: For development environments.
- **`test`**: For testing environments.
- **`prod`**: For production environments.

### Switching Profiles

- **Development (`dev`)**: Activate the `dev` profile using:
  ```bash
  ./run.sh dev
  ```
- **Testing (`test`)**: Activate the `test` profile using:
  ```bash
  ./run.sh test
  ```
- **Production (`prod`)**: Activate the `prod` profile using:
  ```bash
  ./run.sh prod
  ```

## Environment Variables
### Environment Configuration

The application uses the `.env` file to manage environment-specific configurations. You can set the desired Spring profile (`dev`, `test`, or `prod`) for both local development and Dockerized environments by modifying the `SPRING_PROFILES_ACTIVE` environment variable.

By default, the `dev` profile is active for local development:
```properties
SPRING_PROFILES_ACTIVE=dev
```

## Configuration Files

### Integration Tests
- The file `src/main/resources/application-test.properties` is used for integration tests that run the actual application in a `test` environment.
- This file contains configurations specific to the `test` stage, such as database credentials and server port.

### Unit Tests
- The files in `src/test/resources` are used for unit tests and are isolated from the main application configuration.
- For example, `src/test/resources/application-test.properties` contains configurations for unit tests, such as in-memory databases or mock services.

## Endpoints

- **Base URL**:
  - Localhost: `https://localhost:8443/flightreservation-springboot`
  - Docker: `https://localhost:8443/flightreservation-springboot`

- **Actuator Endpoints**:
  - Health: `/actuator/health`
  - Mappings: `/actuator/mappings`

## HTTP and HTTPS Ports

- **HTTP Port**: The application listens on port `8080` for HTTP traffic by default. This port is used to redirect HTTP requests to HTTPS.
- **HTTPS Port**: The application listens on port `8443` for HTTPS traffic. This port is configured using the `APP_PORT` environment variable.

To change the HTTP port, set the `HTTP_PORT` environment variable in the `.env` file:
```properties
HTTP_PORT=8081
```

## License

This project is licensed under the MIT License.