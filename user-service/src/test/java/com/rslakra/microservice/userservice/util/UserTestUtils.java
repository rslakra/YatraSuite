package com.rslakra.microservice.userservice.util;

import static com.rslakra.frameworks.core.RandomUtils.nextRandomEmail;
import static com.rslakra.frameworks.core.RandomUtils.nextRandomFirstName;
import static com.rslakra.frameworks.core.RandomUtils.nextRandomLastName;
import static com.rslakra.frameworks.core.RandomUtils.nextRandomPhoneNumber;

import com.rslakra.microservice.userservice.persistence.entity.Phone;
import com.rslakra.microservice.userservice.persistence.entity.User;
import com.rslakra.microservice.userservice.dto.AuthRequestDTO;
import com.rslakra.microservice.userservice.dto.UserDTO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum UserTestUtils {
    INSTANCE;

    /**
     * @param user
     * @return
     */
    public static List<String> toPhoneNumbers(User user) {
        return user.getPhones().stream().map(phone -> phone.getNumber()).collect(Collectors.toList());
    }

    /**
     * @param user
     * @return
     */
    public static String[] toPhonesArray(User user) {
        return toPhoneNumbers(user).toArray(new String[0]);
    }

    public static User createUser() {
        User user = new User();
        user.setEmail(nextRandomEmail());
        user.setFirstName(nextRandomFirstName());
        user.setLastName(nextRandomLastName());
        user.addPhone(new Phone(user.getId(), nextRandomPhoneNumber()));
        user.addPhone(new Phone(user.getId(), nextRandomPhoneNumber()));
        return user;
    }

    public static UserDTO createUserDTO() {
        UserDTO dto = new UserDTO();
        dto.setEmail(nextRandomEmail());
        dto.setFirstName(nextRandomFirstName());
        dto.setLastName(nextRandomLastName());
        dto.setPhoneNumbers(Arrays.asList(nextRandomPhoneNumber(), nextRandomPhoneNumber(), nextRandomPhoneNumber()));
        return dto;
    }

    public static UserDTO toUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhoneNumbers(toPhoneNumbers(user));
        return dto;
    }

    public static AuthRequestDTO createCredentialsDTO() {
        AuthRequestDTO dto = new AuthRequestDTO();
        dto.setEmail(nextRandomEmail());
        return dto;
    }

}
