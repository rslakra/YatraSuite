package com.rslakra.microservice.rideservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Rohtash Lakra
 * @created 8/4/23 9:33 PM
 */
@Controller
public class IndexController {

    /**
     * Index Page
     *
     * @return
     */
    @GetMapping({"/", "index"})
    public String indexPage() {
        return "index.html";
    }

}