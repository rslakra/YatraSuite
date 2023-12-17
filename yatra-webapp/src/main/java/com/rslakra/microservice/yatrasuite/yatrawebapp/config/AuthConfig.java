package com.rslakra.microservice.yatrasuite.yatrawebapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("auth-service")
public class AuthConfig extends ServiceConfig {

    public AuthConfig() {
        super();
    }

    /**
     * @param baseServiceUrl
     * @param port
     */
    public AuthConfig(String baseServiceUrl, int port) {
        super(baseServiceUrl, port);
    }
}
