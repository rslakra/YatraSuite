package com.rslakra.microservice.yatrathymeleaf.config;

import com.rslakra.microservice.yatrathymeleaf.framework.ui.config.AbstractServiceConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("user-service")
public class UserServiceConfig extends AbstractServiceConfig {

    public UserServiceConfig() {
        super();
    }

    /**
     * @param baseServiceUrl
     * @param port
     */
    public UserServiceConfig(String baseServiceUrl, int port) {
        super(baseServiceUrl, port);
    }
}
