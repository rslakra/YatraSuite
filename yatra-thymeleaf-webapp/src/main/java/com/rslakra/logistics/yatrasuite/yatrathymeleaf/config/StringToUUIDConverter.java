package com.rslakra.logistics.yatrasuite.yatrathymeleaf.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * Custom converter to handle empty strings to UUID conversion.
 * Spring Boot 3.x has stricter type conversion and doesn't allow empty string to UUID by default.
 *
 * @author Rohtash Lakra
 */
@Component
public class StringToUUIDConverter implements Converter<String, UUID> {

    @Override
    public UUID convert(String source) {
        if (!StringUtils.hasText(source)) {
            return null;  // Return null for empty or blank strings
        }
        return UUID.fromString(source.trim());
    }
}

