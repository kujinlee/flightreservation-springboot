package com.example.flightreservation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class HttpToHttpsRedirectConfig {

    @Value("${HTTP_PORT:8080}") // Dynamically retrieve the HTTP port from the environment or use default
    private int httpPort;

    @Value("${APP_PORT:8443}") // Dynamically retrieve the HTTPS port from the environment or use default
    private int httpsPort;

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        System.out.println("HTTP Port for redirection: " + httpPort); // Log the HTTP port
        return factory -> {
            if (factory instanceof TomcatServletWebServerFactory tomcatFactory) {
                tomcatFactory.addAdditionalTomcatConnectors(createHttpConnector());
            }
        };
    }

    private org.apache.catalina.connector.Connector createHttpConnector() {
        org.apache.catalina.connector.Connector connector = new org.apache.catalina.connector.Connector(org.apache.coyote.http11.Http11NioProtocol.class.getName());
        connector.setScheme("http");
        connector.setPort(httpPort); // Use dynamically retrieved HTTP port
        connector.setSecure(false);
        connector.setRedirectPort(httpsPort); // Redirect to dynamically retrieved HTTPS port
        return connector;
    }

    @Bean
    public Filter invalidHttpsRequestFilter() {
        return (request, response, chain) -> {
            if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
                HttpServletRequest httpRequest = (HttpServletRequest) request;
                HttpServletResponse httpResponse = (HttpServletResponse) response;

                // Check if the request is HTTPS on the HTTP port
                if ("https".equalsIgnoreCase(httpRequest.getScheme()) && httpRequest.getLocalPort() == httpPort) {
                    httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid HTTPS request sent to HTTP port.");
                    return;
                }
            }
            chain.doFilter(request, response);
        };
    }
}
