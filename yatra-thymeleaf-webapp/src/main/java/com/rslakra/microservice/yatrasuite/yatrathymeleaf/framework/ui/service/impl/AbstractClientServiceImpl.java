package com.rslakra.microservice.yatrasuite.yatrathymeleaf.framework.ui.service.impl;

import com.devamatre.appsuite.spring.client.ApiRestClient;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.framework.ui.service.AbstractClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rohtash Lakra
 * @created 7/11/23 8:09 AM
 */
public abstract class AbstractClientServiceImpl<T, ID> implements AbstractClientService<T, ID> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractClientServiceImpl.class);
    private final ApiRestClient apiRestClient;

    /**
     * @param apiRestClient
     */
    public AbstractClientServiceImpl(ApiRestClient apiRestClient) {
        LOGGER.debug("AbstractClientServiceImpl({})", apiRestClient);
        this.apiRestClient = apiRestClient;
    }

    /**
     * @return
     */
    public final ApiRestClient getRestClient() {
        return apiRestClient;
    }

}
