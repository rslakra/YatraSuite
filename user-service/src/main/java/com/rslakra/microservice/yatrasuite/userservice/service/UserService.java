package com.rslakra.microservice.yatrasuite.userservice.service;

import com.devamatre.appsuite.core.enums.RoleType;
import com.devamatre.appsuite.spring.service.AbstractService;
import com.rslakra.microservice.yatrasuite.common.exception.NotFoundException;
import com.rslakra.microservice.yatrasuite.common.exception.UserAlreadyExistsException;
import com.rslakra.microservice.yatrasuite.userservice.persistence.entity.User;

import java.util.List;
import java.util.Set;

/**
 * @author Rohtash Lakra
 * @created 8/20/21 8:11 PM
 */
public interface UserService extends AbstractService<User, Long> {

    /**
     * Returns the list of users by firstName.
     *
     * @param firstName
     * @return
     */
    public List<User> getByFirstName(String firstName);

    /**
     * Returns the list of users by lastName.
     *
     * @param lastName
     * @return
     */
    public List<User> getByLastName(String lastName);

    /**
     * Returns the list of users by email.
     *
     * @param email
     * @return
     */
    public User getByEmail(String email);

    /**
     * Returns the list of users by email.
     *
     * @param emailList
     * @return
     */
    public List<User> getByEmails(List<String> emailList);

    /**
     * @param user
     * @param roleTypes
     * @return
     */
    public void addRoles(User user, RoleType... roleTypes);

    /**
     * Returns true if the <code>user</code> has the provided <code>roleTypes</code> otherwise false.
     *
     * @param user
     * @param roleTypes
     * @return
     */
    public boolean hasRoles(User user, Set<RoleType> roleTypes);

    /**
     * Refactor this code later
     */

    /**
     * @param email
     * @return
     * @throws NotFoundException
     */
    User getUser(String email) throws NotFoundException;

    /**
     * @param email
     * @param password
     * @param firstName
     * @param lastName
     * @param phoneNumbers
     * @return
     * @throws UserAlreadyExistsException
     */
    User addUser(String email, String password, String firstName, String lastName, List<String> phoneNumbers)
        throws UserAlreadyExistsException;

    /**
     * @param email
     * @throws NotFoundException
     */
    void delete(String email) throws NotFoundException;

}
