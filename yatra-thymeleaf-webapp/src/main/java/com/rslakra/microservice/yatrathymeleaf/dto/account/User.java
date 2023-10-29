package com.rslakra.microservice.yatrathymeleaf.dto.account;

import com.rslakra.frameworks.core.BeanUtils;
import com.rslakra.frameworks.core.ToString;
import com.rslakra.frameworks.core.enums.EntityStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

/**
 * @author Rohtash Lakra
 * @created 8/20/21 7:14 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class User extends Person {

    private String password;
    @Enumerated(value = EnumType.STRING)
    private EntityStatus status = EntityStatus.INACTIVE;
    private Set<String> phoneNumbers;

    @Transient
    private Set<Phone> phones = new HashSet<>();

    /**
     * @param phoneNumbers
     */
    public void setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
        if (BeanUtils.isNotEmpty(getPhoneNumbers())) {
            getPhoneNumbers().stream().forEach(phone -> addPhone(new Phone(getId(), phone)));
        }
    }

    /**
     * @param phones
     */
    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
        if (BeanUtils.isNotEmpty(getPhones())) {
            this.phoneNumbers = this.phones.stream()
                .map(Phone::getNumber)
                .collect(Collectors.toSet());
        }
    }

    /**
     * @param phone
     */
    public void addPhone(final Phone phone) {
        if (BeanUtils.isNotNull(phone)) {
            phone.setUserId(getId());
            getPhones().add(phone);
        }
    }

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(User.class)
            .add("id", getId())
            .add("email", getEmail())
            .add("firstName", getFirstName())
            .add("middleName", getMiddleName())
            .add("lastName", getLastName())
            .add("status", getStatus())
            .add("phoneNumbers", getPhoneNumbers())
            .toString();
    }
}
