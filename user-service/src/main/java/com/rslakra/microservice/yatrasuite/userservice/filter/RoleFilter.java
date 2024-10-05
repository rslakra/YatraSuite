package com.rslakra.microservice.yatrasuite.userservice.filter;

import com.rslakra.appsuite.core.Payload;
import com.rslakra.appsuite.spring.filter.AbstractFilter;
import com.rslakra.microservice.yatrasuite.userservice.persistence.entity.Role;

import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 2/8/23 1:06 PM
 */
public final class RoleFilter extends AbstractFilter<Role> {

    /**
     * @param allParams
     */
    public RoleFilter(Payload<String, Object> allParams) {
        super(allParams);
    }

    /**
     * @param allParams
     */
    public RoleFilter(Map<String, Object> allParams) {
        super(allParams);
    }

}
