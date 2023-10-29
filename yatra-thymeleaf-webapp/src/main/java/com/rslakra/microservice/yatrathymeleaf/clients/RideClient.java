package com.rslakra.microservice.yatrathymeleaf.clients;

import com.rslakra.microservice.yatrathymeleaf.framework.ui.config.AbstractServiceConfig;
import com.rslakra.microservice.yatrathymeleaf.config.RideServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class RideClient extends AbstractRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserClient.class);

    private RideServiceConfig serviceConfig;

    /**
     * @param restTemplateBuilder
     * @param serviceConfig
     */
    @Autowired
    public RideClient(RestTemplateBuilder restTemplateBuilder, RideServiceConfig serviceConfig) {
        super(restTemplateBuilder);
        this.serviceConfig = serviceConfig;
    }

    /**
     * @return
     */
    @Override
    public AbstractServiceConfig getServiceConfig() {
        return serviceConfig;
    }

    /**
     * @param request
     * @return
     */
    public Map<String, Object> startRide(Map<String, Object> request) {
        LOGGER.debug("startRide({})", request);
        String uri = getServiceConfig().getRestUrl("/rides/start");
        Map<String, Object> response = getRestTemplate().postForObject(uri, request, Map.class);
        return response;
    }

    /**
     * @param request
     * @return
     */
    public Map<String, Object> endRide(Map<String, Object> request) {
        LOGGER.debug("endRide({})", request);
        String uri = getServiceConfig().getRestUrl("/rides/end");
        Map<String, Object> response = getRestTemplate().postForObject(uri, request, Map.class);
        return response;
    }

    /**
     * @param email
     * @return
     */
    public List<Map<String, Object>> getRides(String email) {
        LOGGER.debug("getRides({})", email);
        String uri = getServiceConfig().getRestUrl(String.format("/rides?email=%s", email));
        Map<String, Object>[] rides = getRestTemplate().getForObject(uri, Map[].class);
        return Arrays.asList(rides);
    }
}
