package com.rslakra.microservice.yatrawebapp.clients;

import com.rslakra.microservice.yatrawebapp.config.VehicleConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class VehicleClient extends AbstractRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleClient.class);

    private VehicleConfig config;

    @Autowired
    public VehicleClient(RestTemplateBuilder restTemplateBuilder, VehicleConfig config) {
        super(restTemplateBuilder);
        this.config = config;
    }

    /**
     * @param newVehicleDTO
     * @return
     */
    public Map<String, Object> addVehicle(Map<String, Object> newVehicleDTO) {
        LOGGER.debug("addVehicle({})", newVehicleDTO);
        String uri = config.getServiceUrl("/vehicles");
        Map<String, Object> response = getRestTemplate().postForObject(uri, newVehicleDTO, Map.class);
        return response;
    }

    /**
     * @param vehicleId
     * @return
     */
    public Map<String, Object> removeVehicle(UUID vehicleId) {
        LOGGER.debug("removeVehicle({})", vehicleId);
        String uri = config.getServiceUrl(String.format("/vehicles/%s", vehicleId));
        // Using an exchange instead of a delete because it allows me to return a body. Delete just returns void.
        Map<String, Object> response = getRestTemplate().exchange(uri, HttpMethod.DELETE, null, Map.class).getBody();
        return response;
    }

    /**
     * @param maxVehicles
     * @return
     */
    public List<Map<String, Object>> getVehiclesWithLocation(Integer maxVehicles) {
        LOGGER.debug("getVehiclesWithLocation({})", maxVehicles);
        String uri = config.getServiceUrl(String.format("/vehicles?max_vehicles=%s", maxVehicles));
        Map<String, Object>[] response = getRestTemplate().getForObject(uri, Map[].class);
        return Arrays.asList(response);
    }

    /**
     * @param vehicleId
     * @return
     */
    public Map<String, Object> getVehicleWithLocation(UUID vehicleId) {
        LOGGER.debug("getVehicleWithLocation({})", vehicleId);
        String uri = config.getServiceUrl(String.format("/vehicles/location/%s", vehicleId.toString()));
        Map<String, Object> response = getRestTemplate().getForObject(uri, Map.class);
        return response;
    }

    /**
     * @param vehicleId
     * @return
     */
    public Map<String, Object> getVehicleWithHistory(UUID vehicleId) {
        LOGGER.debug("getVehicleWithHistory({})", vehicleId);
        String uri = config.getServiceUrl(String.format("/vehicles/%s", vehicleId.toString()));
        Map<String, Object> response = getRestTemplate().getForObject(uri, Map.class);
        return response;
    }
}
