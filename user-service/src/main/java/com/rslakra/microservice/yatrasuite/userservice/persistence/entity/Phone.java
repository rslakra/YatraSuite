package com.rslakra.microservice.yatrasuite.userservice.persistence.entity;

import com.devamatre.appsuite.core.ToString;
import com.devamatre.appsuite.spring.persistence.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Rohtash Lakra
 * @created 7/25/23 7:52 AM
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "phones")
public class Phone extends AbstractEntity<Long> {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "number")
    private String number;

    @Column(name = "ext")
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
            .add("userId", getUserId())
            .add("number", getNumber())
            .add("ext", getExt())
            .toString();
    }

}
