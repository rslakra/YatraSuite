package com.rslakra.microservice.userservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer object to pass login info (in an actual application, this would include other authorization information
 * such as password)
 *
 * @author Rohtash Lakra
 * @created 7/26/23 1:45 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthRequestDTO {

    private String email;

}
