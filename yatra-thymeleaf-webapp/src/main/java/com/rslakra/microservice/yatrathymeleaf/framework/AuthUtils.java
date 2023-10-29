package com.rslakra.microservice.yatrathymeleaf.framework;

import com.rslakra.frameworks.core.BeanUtils;
import com.rslakra.microservice.yatrathymeleaf.security.AuthUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Rohtash Lakra
 * @created 8/9/23 2:16 PM
 */
public enum AuthUtils {
    INSTANCE;

    private Authentication authentication;

    /**
     * @return
     */
    public Authentication getAuthentication() {
        return authentication;
    }

    /**
     * @param authentication
     */
    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    /**
     * Returns the <code>AuthUserDetails</code> object.
     *
     * @return
     */
    public static AuthUserDetails getAuthUser() {
        AuthUserDetails authUser = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (BeanUtils.isNotNull(authentication)) {
            authUser = (AuthUserDetails) authentication.getPrincipal();
        }

        return authUser;
    }

    /**
     * @return
     */
    public static String getUserEmail() {
        AuthUserDetails authUser = getAuthUser();
        return (BeanUtils.isNull(authUser) ? null : authUser.getEmail());
    }
}
