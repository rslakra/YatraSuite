package com.rslakra.microservice.yatrasuite.yatrathymeleaf.controller;

import com.rslakra.appsuite.core.BeanUtils;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.dto.account.LoginRequest;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.dto.account.RegisterRequest;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.security.AuthUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    public static final String VIEW_INDEX = "views/index";
    public static final String VIEW_LOGIN = "views/login";
    public static final String VIEW_REGISTER = "views/register";

    /**
     * @return
     */
    @GetMapping(value = {"/", "/index"})
    public String indexPage(Model model) {
        LOGGER.debug("+indexPage({})", model);
        /**
         * Check user is authenticated or not.
         */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (BeanUtils.isNotNull(authentication)) {
            AuthUserDetails authUser = (AuthUserDetails) authentication.getPrincipal();
            LOGGER.debug("authUser: {}", authUser);
            model.addAttribute("authUser", authUser);
        }

        LOGGER.debug("-indexPage(), viewIndex: {}", VIEW_INDEX);
        return VIEW_INDEX;
    }

    /**
     * @return
     */
    @GetMapping(value = {"/login", "/sign-in"})
    public String loginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return VIEW_LOGIN;
    }

    /**
     * @return
     */
    @GetMapping(value = {"/register", "/sign-up"})
    public String registerPage(Model model) {
        model.addAttribute("loginRequest", new RegisterRequest());
        return VIEW_REGISTER;
    }

}
