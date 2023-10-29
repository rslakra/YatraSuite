package com.rslakra.microservice.yatrawebapp.clients;

import com.rslakra.microservice.yatrawebapp.config.RidesConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class RidesClient extends AbstractRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserClient.class);

    private RidesConfig config;

    @Autowired
    public RidesClient(RestTemplateBuilder restTemplateBuilder, RidesConfig config) {
        super(restTemplateBuilder);
        this.config = config;
    }

    /**
     * @param request
     * @return
     */
    public Map<String, Object> startRide(Map<String, Object> request) {
        LOGGER.debug("startRide({})", request);
        String uri = config.getServiceUrl("/rides/start");
        Map<String, Object> response = getRestTemplate().postForObject(uri, request, Map.class);
        return response;
    }

    /**
     * @param request
     * @return
     */
    public Map<String, Object> endRide(Map<String, Object> request) {
        LOGGER.debug("endRide({})", request);
        String uri = config.getServiceUrl("/rides/end");
        Map<String, Object> response = getRestTemplate().postForObject(uri, request, Map.class);
        return response;
    }

    /**
     * @param email
     * @return
     */
    public List<Map<String, Object>> getRides(String email) {
        LOGGER.debug("getRides({})", email);
        String uri = config.getServiceUrl(String.format("/rides?email=%s", email));
        Map<String, Object>[] rides = getRestTemplate().getForObject(uri, Map[].class);
        return Arrays.asList(rides);
    }
}
