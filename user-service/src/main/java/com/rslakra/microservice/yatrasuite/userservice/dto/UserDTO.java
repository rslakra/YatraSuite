package com.rslakra.microservice.yatrasuite.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Data Transfer Object for User Entity
 *
 * @author Rohtash Lakra
 * @created 7/26/23 1:45 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    @JsonProperty("email")
    private String email;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("phone_numbers")
    private List<String> phoneNumbers;

}
