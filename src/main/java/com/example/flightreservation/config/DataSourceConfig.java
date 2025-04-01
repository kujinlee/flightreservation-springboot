package com.example.flightreservation.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

    private final Environment environment;

    public DataSourceConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
        // Use MYSQL_HOST directly from the environment
        String mysqlHost = environment.getProperty("MYSQL_HOST", "localhost");

        // Construct the datasource URL dynamically
        String datasourceUrl = String.format("jdbc:mysql://%s:3306/reservation", mysqlHost);
        String datasourceUsername = environment.getProperty("spring.datasource.username");
        String datasourcePassword = environment.getProperty("spring.datasource.password");

        // Log missing variables
        if (datasourceUsername == null) {
            System.err.println("Environment variable spring.datasource.username is missing.");
        }
        if (datasourcePassword == null) {
            System.err.println("Environment variable spring.datasource.password is missing.");
        }

        // Configure HikariDataSource
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(datasourceUrl);
        dataSource.setUsername(datasourceUsername);
        dataSource.setPassword(datasourcePassword);

        System.out.println("Configured spring.datasource.url: " + datasourceUrl);

        return dataSource;
    }
}
