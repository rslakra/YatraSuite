package com.rslakra.microservice.yatrasuite.yatrawebapp.clients;

import com.rslakra.microservice.yatrasuite.framework.advice.RestTemplateResponseErrorHandler;
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

}
