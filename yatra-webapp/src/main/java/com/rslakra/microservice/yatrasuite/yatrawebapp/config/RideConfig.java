package com.rslakra.microservice.yatrasuite.yatrawebapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("ride-service")
public class RideConfig extends ServiceConfig {

    public RideConfig() {
        super();
    }

    /**
     * @param baseServiceUrl
     * @param port
     */
    public RideConfig(String baseServiceUrl, Integer port) {
        super(baseServiceUrl, port);
    }
}
