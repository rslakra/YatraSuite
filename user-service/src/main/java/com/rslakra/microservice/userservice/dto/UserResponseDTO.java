package com.rslakra.microservice.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rslakra.microservice.common.dto.MessagesDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object ot pass profile responses back to the front-end
 *
 * @author Rohtash Lakra
 * @created 7/26/23 1:45 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    @JsonProperty("user")
    private UserDTO userDTO;
    @JsonProperty("messages")
    private MessagesDTO messagesDTO;

}
