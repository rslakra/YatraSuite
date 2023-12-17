package com.rslakra.microservice.yatrasuite.yatrathymeleaf.config;

import com.rslakra.microservice.yatrasuite.yatrathymeleaf.framework.ui.config.AbstractServiceConfig;
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
