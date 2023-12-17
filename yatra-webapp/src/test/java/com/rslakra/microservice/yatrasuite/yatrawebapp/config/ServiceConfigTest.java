package com.rslakra.microservice.yatrasuite.yatrawebapp.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ServiceConfigTest {

    @Test
    public void uri_shouldReturnTheFormattedURI() {
        ServiceConfig config = new ServiceConfig("host", 1234) {
        };
        assertEquals("http://host:1234/suffix", config.getServiceUrl("/suffix"));
    }

}