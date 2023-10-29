package com.rslakra.microservice.yatrawebapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("rides-service")
public class RidesConfig extends ServiceConfig {

    public RidesConfig() {
        super();
    }

    /**
     * @param baseServiceUrl
     * @param port
     */
    public RidesConfig(String baseServiceUrl, Integer port) {
        super(baseServiceUrl, port);
    }
}
