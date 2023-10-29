package com.rslakra.microservice.yatrawebapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("vehicle-service")
public class VehicleConfig extends ServiceConfig {

    public VehicleConfig() {
        super();
    }

    /**
     * @param baseServiceUrl
     * @param port
     */
    public VehicleConfig(String baseServiceUrl, int port) {
        super(baseServiceUrl, port);
    }
}
