package com.rslakra.microservice.userservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
public class UserServiceApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceApplication.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
        LOGGER.info("*** User Service API Started ***");
    }
}
