package com.rslakra.microservice.yatrasuite.framework.advice;

import com.devamatre.appsuite.core.BeanUtils;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Data transfer object to hold error or status messages
 */

@Getter
@SuperBuilder
public class AbstractResponse {

    private Integer statusCode;
    private List<String> messages;

    /**
     * @param statusCode
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @param message
     */
    public void addMessage(String message) {
        if (BeanUtils.isNull(messages)) {
            messages = new ArrayList<>();
        }

        this.messages.add(message);
    }

}
