package com.rslakra.microservice.yatrawebapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("user-service")
public class UserConfig extends ServiceConfig {

    public UserConfig() {
        super();
    }

    /**
     * @param baseServiceUrl
     * @param port
     */
    public UserConfig(String baseServiceUrl, int port) {
        super(baseServiceUrl, port);
    }
}
