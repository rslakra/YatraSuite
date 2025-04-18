package com.rslakra.logistics.yatrasuite.yatrathymeleaf.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.rslakra.logistics.yatrasuite.yatrathymeleaf.framework.ui.config.AbstractServiceConfig;
import org.junit.jupiter.api.Test;

class AbstractServiceConfigTest {

    @Test
    public void uri_shouldReturnTheFormattedURI() {
        AbstractServiceConfig config = new AbstractServiceConfig("host", 1234) {
        };
        assertEquals("http://host:1234/suffix", config.getRestUrl("/suffix"));
    }

}