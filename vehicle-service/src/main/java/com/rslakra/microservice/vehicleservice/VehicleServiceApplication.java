package com.rslakra.microservice.vehicleservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class VehicleServiceApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleServiceApplication.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(VehicleServiceApplication.class, args);
        LOGGER.info("*** Vehicle Service API Started ***");
    }
}
