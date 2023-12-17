package com.rslakra.microservice.yatrasuite.yatrawebapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@EnableConfigurationProperties
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class YatraWebAppApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(YatraWebAppApplication.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(YatraWebAppApplication.class, args);
        LOGGER.info("*** Yatra Webapp UI Gateway Started ***");
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder, ObjectMapper mapper) {
        return builder
            .messageConverters(new MappingJackson2HttpMessageConverter(mapper))
            .build();
    }
}
