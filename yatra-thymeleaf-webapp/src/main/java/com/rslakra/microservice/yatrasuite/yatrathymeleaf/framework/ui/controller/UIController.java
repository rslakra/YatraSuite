package com.rslakra.microservice.yatrasuite.yatrathymeleaf.framework.ui.controller;

import com.rslakra.microservice.yatrasuite.yatrathymeleaf.clients.AbstractRestClient;

/**
 * @author Rohtash Lakra
 * @created 8/4/23 7:01 PM
 */
public interface UIController {

    /**
     * Returns the <code>AbstractRestClient</code> children object.
     *
     * @return
     */
    AbstractRestClient getRestClient();

}
