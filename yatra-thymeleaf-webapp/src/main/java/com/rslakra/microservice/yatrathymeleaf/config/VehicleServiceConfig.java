package com.rslakra.microservice.yatrathymeleaf.config;

import com.rslakra.microservice.yatrathymeleaf.framework.ui.config.AbstractServiceConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("vehicle-service")
public class VehicleServiceConfig extends AbstractServiceConfig {

    public VehicleServiceConfig() {
        super();
    }

    /**
     * @param baseServiceUrl
     * @param port
     */
    public VehicleServiceConfig(String baseServiceUrl, int port) {
        super(baseServiceUrl, port);
    }
}
