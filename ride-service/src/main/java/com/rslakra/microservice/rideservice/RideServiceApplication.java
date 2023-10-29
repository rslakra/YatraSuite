package com.rslakra.microservice.rideservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Rohtash Lakra
 * @created 7/23/23 9:16 PM
 */
@SpringBootApplication
@EnableTransactionManagement
public class RideServiceApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(RideServiceApplication.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(RideServiceApplication.class, args);
        LOGGER.info("*** Ride Service API Started ***");
    }
}
