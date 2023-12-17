package com.rslakra.microservice.yatrasuite.userservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer object containing info for an authenticated user (in a real app this would contain session info or a
 * token of some sort)
 *
 * @author Rohtash Lakra
 * @created 7/26/23 1:45 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthResponseDTO {

    private boolean isAuthenticated;

}
