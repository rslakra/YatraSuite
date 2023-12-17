package com.rslakra.microservice.yatrasuite.yatrathymeleaf.dto.account;

import com.devamatre.appsuite.core.ToString;
import com.devamatre.appsuite.spring.payload.dto.AbstractEntityDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra
 * @created 7/25/23 7:52 AM
 */
@Getter
@Setter
@NoArgsConstructor
public class Phone extends AbstractEntityDTO<Long> {

    private Long userId;
    private String number;
    private String ext;

    /**
     * @param userId
     * @param phoneNumber
     */
    public Phone(Long userId, String phoneNumber) {
        this.userId = userId;
        this.number = phoneNumber;
    }

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(Phone.class)
            .add("number", getNumber())
            .add("ext", getExt())
            .toString();
    }

}
