package com.rslakra.microservice.yatrasuite.yatrathymeleaf.config;

import com.rslakra.microservice.yatrasuite.yatrathymeleaf.framework.ui.config.AbstractServiceConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("auth-service")
public class AuthServiceConfig extends AbstractServiceConfig {

    public AuthServiceConfig() {
        super();
    }

    /**
     * @param baseServiceUrl
     * @param port
     */
    public AuthServiceConfig(String baseServiceUrl, int port) {
        super(baseServiceUrl, port);
    }
}
