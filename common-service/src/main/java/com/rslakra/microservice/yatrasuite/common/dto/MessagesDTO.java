package com.rslakra.microservice.yatrasuite.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Data transfer object to hold error or status messages
 *
 * @author Rohtash Lakra
 * @created 7/26/23 1:45 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessagesDTO {

    private List<String> messages = new ArrayList<>();

    /**
     * @param message
     */
    public void addMessage(String message) {
        messages.add(message);
    }

}
