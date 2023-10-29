package com.rslakra.microservice.userservice.filter;

import com.devamatre.framework.core.Payload;
import com.devamatre.framework.spring.filter.AbstractFilterImpl;

import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 2/8/23 1:06 PM
 */
public final class UserFilter extends AbstractFilterImpl {

    /**
     * @param allParams
     */
    public UserFilter(final Payload<String, Object> allParams) {
        super(allParams);
    }

    /**
     * @param allParams
     */
    public UserFilter(Map<String, Object> allParams) {
        super(allParams);
    }
}
