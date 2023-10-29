package com.rslakra.microservice.yatrathymeleaf.config;

import com.rslakra.microservice.yatrathymeleaf.framework.ui.config.AbstractServiceConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("role-service")
public class RoleServiceConfig extends AbstractServiceConfig {

    public RoleServiceConfig() {
        super();
    }

    /**
     * @param baseServiceUrl
     * @param port
     */
    public RoleServiceConfig(String baseServiceUrl, int port) {
        super(baseServiceUrl, port);
    }
}
