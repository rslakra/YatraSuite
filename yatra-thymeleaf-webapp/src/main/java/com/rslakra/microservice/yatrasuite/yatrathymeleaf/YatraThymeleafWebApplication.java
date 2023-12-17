package com.rslakra.microservice.yatrasuite.yatrathymeleaf;

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
public class YatraThymeleafWebApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(YatraThymeleafWebApplication.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(YatraThymeleafWebApplication.class, args);
        LOGGER.info("*** Yatra Thymeleaf Webapp UI Gateway Started ***");
    }

    /**
     * @param builder
     * @param mapper
     * @return
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder, ObjectMapper mapper) {
        return builder
            .messageConverters(new MappingJackson2HttpMessageConverter(mapper))
            .build();
    }
}
