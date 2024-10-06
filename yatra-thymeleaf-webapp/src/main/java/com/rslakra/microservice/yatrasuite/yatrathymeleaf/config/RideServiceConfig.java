package com.rslakra.microservice.yatrasuite.yatrathymeleaf.config;

import com.rslakra.microservice.yatrasuite.yatrathymeleaf.framework.ui.config.AbstractServiceConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("ride-service")
public class RideServiceConfig extends AbstractServiceConfig {

    public RideServiceConfig() {
        super();
    }

    /**
     * @param baseServiceUrl
     * @param port
     */
    public RideServiceConfig(String baseServiceUrl, Integer port) {
        super(baseServiceUrl, port);
    }
}
