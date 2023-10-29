package com.rslakra.microservice.yatrathymeleaf.clients;

import com.rslakra.microservice.framework.advice.RestTemplateResponseErrorHandler;
import com.rslakra.microservice.yatrathymeleaf.framework.ui.config.AbstractServiceConfig;
import lombok.Getter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

/**
 * @author Rohtash Lakra
 * @created 8/3/23 8:22 AM
 */
@Getter
public abstract class AbstractRestClient {

    private final RestTemplate restTemplate;

    /**
     * @param restTemplateBuilder
     */
    public AbstractRestClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
            .errorHandler(new RestTemplateResponseErrorHandler())
            .build();
    }

    /**
     * @return
     */
    public abstract AbstractServiceConfig getServiceConfig();

}
