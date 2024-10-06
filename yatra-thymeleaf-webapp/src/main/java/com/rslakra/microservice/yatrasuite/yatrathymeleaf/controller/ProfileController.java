package com.rslakra.microservice.yatrasuite.yatrathymeleaf.controller;

import com.rslakra.appsuite.core.BeanUtils;
import com.rslakra.appsuite.spring.controller.web.AbstractWebController;
import com.rslakra.appsuite.spring.filter.Filter;
import com.rslakra.appsuite.spring.parser.Parser;
import com.rslakra.microservice.yatrasuite.common.exception.NotFoundException;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.dto.account.User;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.framework.AuthUtils;
import com.rslakra.microservice.yatrasuite.yatrathymeleaf.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

/**
 * REST Controller to manage user activities
 */

@Controller
@RequestMapping("${uiPrefix}/profile")
public class ProfileController extends AbstractWebController<User, Long> {

    private static final Logger LOGGER = LogManager.getLogger(ProfileController.class);
    public static final String VIEW_USER_PROFILE = "views/user/userProfile";
    public static final String MODEL_ATTR_USER = "user";

    private final UserService userService;

    /**
     * @param userService
     */
    @Autowired
    public ProfileController(UserService userService) {
        LOGGER.debug("ProfileController({})", userService);
        this.userService = userService;
    }

    /**
     * @return
     */
    @Override
    public Parser<User> getParser() {
        return null;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public String save(User user) {
        return null;
    }

    /**
     * @param model
     * @return
     */
    @Override
    public String getAll(Model model) {
        return null;
    }

    /**
     * Gets a user.
     *
     * @return Json with the details about the user
     * @throws NotFoundException if the email does not exist
     */
    @GetMapping
    public String getProfile(Model model) throws NotFoundException {
        LOGGER.debug("+getProfile()");
        String email = AuthUtils.getUserEmail();
        LOGGER.info("[GET] /ui/users/{email}", email);
        User user = userService.getByEmail(email);
        if (BeanUtils.isNull(user)) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        model.addAttribute(MODEL_ATTR_USER, user);
        LOGGER.debug("-getProfile(), userProfile:{}, user:{}", VIEW_USER_PROFILE, user);
        return VIEW_USER_PROFILE;
    }

    /**
     * @param model
     * @param filter
     * @return
     */
    @Override
    public String filter(Model model, Filter filter) {
        return null;
    }

    /**
     * @param model
     * @param allParams
     * @return
     */
    @Override
    public String filter(Model model, @RequestParam Map<String, Object> allParams) {
        return null;
    }

    /**
     * @param model
     * @param aLong
     * @return
     */
    @Override
    public String editObject(Model model, Long aLong) {
        return null;
    }

    /**
     * @param model
     * @param aLong
     * @return
     */
    @Override
    public String delete(Model model, Long aLong) {
        return null;
    }
}

