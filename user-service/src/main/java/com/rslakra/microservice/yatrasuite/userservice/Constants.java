package com.rslakra.microservice.yatrasuite.userservice;

/**
 * various constants such as error messages and configuration values that are not expected to change
 */

public interface Constants {

    // list of possible error messages
    String ERR_USER_EMAIL_NOT_FOUND = "User email <%s> not found";
    String ERR_USER_ALREADY_EXISTS = "User email <%s> already exists";

    // success messages
    String MSG_DELETED_EMAIL = "You have successfully deleted your account.";
}
