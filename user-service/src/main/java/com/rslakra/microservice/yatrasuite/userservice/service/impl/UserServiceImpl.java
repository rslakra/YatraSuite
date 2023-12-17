package com.rslakra.microservice.yatrasuite.userservice.service.impl;

import com.devamatre.appsuite.core.BeanUtils;
import com.devamatre.appsuite.core.Payload;
import com.devamatre.appsuite.core.Sets;
import com.devamatre.appsuite.core.enums.EntityStatus;
import com.devamatre.appsuite.core.enums.RoleType;
import com.devamatre.appsuite.spring.exception.DuplicateRecordException;
import com.devamatre.appsuite.spring.exception.InvalidRequestException;
import com.devamatre.appsuite.spring.exception.NoRecordFoundException;
import com.devamatre.appsuite.spring.filter.Filter;
import com.devamatre.appsuite.spring.persistence.Operation;
import com.devamatre.appsuite.spring.service.AbstractServiceImpl;
import com.rslakra.microservice.yatrasuite.common.exception.NotFoundException;
import com.rslakra.microservice.yatrasuite.common.exception.UserAlreadyExistsException;
import com.rslakra.microservice.yatrasuite.userservice.Constants;
import com.rslakra.microservice.yatrasuite.userservice.filter.UserFilter;
import com.rslakra.microservice.yatrasuite.userservice.persistence.entity.Phone;
import com.rslakra.microservice.yatrasuite.userservice.persistence.entity.Role;
import com.rslakra.microservice.yatrasuite.userservice.persistence.entity.User;
import com.rslakra.microservice.yatrasuite.userservice.persistence.repository.PhoneRepository;
import com.rslakra.microservice.yatrasuite.userservice.persistence.repository.RoleRepository;
import com.rslakra.microservice.yatrasuite.userservice.persistence.repository.UserRepository;
import com.rslakra.microservice.yatrasuite.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Rohtash Lakra
 * @created 10/9/21 5:50 PM
 */
@Service
public class UserServiceImpl extends AbstractServiceImpl<User, Long> implements UserService {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final String[] ignoredProperties = new String[]{
        "id", "password", "createdOn", "createdAt", "createdBy", "updatedOn", "updatedAt", "updatedBy"
    };

    private final static ModelMapper MODEL_MAPPER = new ModelMapper();

    // roleRepository
    private final RoleRepository roleRepository;
    // userRepository
    private final UserRepository userRepository;

    private final PhoneRepository phoneRepository;

    /**
     * @param roleRepository
     * @param userRepository
     * @param phoneRepository
     */
    @Autowired
    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository,
                           PhoneRepository phoneRepository) {
        LOGGER.debug("UserServiceImpl({}, {}, {})", roleRepository, userRepository, phoneRepository);
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
    }

    /**
     * Validates the <code>T</code> object.
     *
     * @param operation
     * @param user
     * @return
     */
    @Override
    public User validate(Operation operation, User user) {
        LOGGER.debug("+validate({}, {})", operation, user);
        switch (operation) {
            case CREATE: {
                if (BeanUtils.isEmpty(user.getEmail())) {
                    throw new InvalidRequestException("The user's email should provide!");
                } else if (BeanUtils.isEmpty(user.getFirstName())) {
                    throw new InvalidRequestException("The user's firstName should provide!");
                } else if (BeanUtils.isEmpty(user.getLastName())) {
                    throw new InvalidRequestException("The user's lastName should provide!");
                }

                // check task exists for this song
                if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                    throw new DuplicateRecordException("user");
                }
            }

            break;

            case UPDATE: {
                if (BeanUtils.isNull(user.getId())) {
                    throw new InvalidRequestException("The user's ID should provide!");
                }

                if (BeanUtils.isNotEmpty(user.getEmail())) {
                    // check task exists for this song
                    Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
                    if (userOptional.isPresent() && !userOptional.get().getId().equals(user.getId())) {
                        throw new DuplicateRecordException("user");
                    }
                }

                // update object
                User oldUser = getById(user.getId());
                BeanUtils.copyProperties(user, oldUser, ignoredProperties);
                user = oldUser;
            }

            break;

            default:
                throw new InvalidRequestException("Unsupported Operation!");
        }

        LOGGER.debug("-validate(), user: {}", user);
        return user;
    }

    /**
     * Creates the <code>T</code> object.
     *
     * @param user
     * @return
     */
    @Override
    public User create(User user) {
        LOGGER.debug("+create({})", user);
        user = validate(Operation.CREATE, user);
        user = userRepository.save(user);
        LOGGER.debug("-create(), user: {}", user);
        return user;
    }

    /**
     * Creates the <code>List<T></code> objects.
     *
     * @param users
     * @return
     */
    @Override
    public List<User> create(List<User> users) {
        LOGGER.debug("+create({})", users);
        if (BeanUtils.isEmpty(users)) {
            throw new InvalidRequestException("The users should provide!");
        }

        users.forEach(user -> validate(Operation.CREATE, user));
        users = userRepository.saveAll(users);

        LOGGER.debug("-create(), users: {}", users);
        return users;
    }

    /**
     * Returns the list of all <code>T</code> objects.
     *
     * @return
     */
    @Override
    public List<User> getAll() {
        LOGGER.debug("+getAll()");
        List<User> users = userRepository.findAll();
        LOGGER.debug("+getAll(), users:{}", users);
        return users;
    }

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @param filter
     * @return
     */
    @Override
    public List<User> getByFilter(Filter filter) {
        LOGGER.debug("+getByFilter({})", filter);
        List<User> users = Collections.emptyList();
        if (filter.hasKeys(UserFilter.EMAIL, UserFilter.FIRST_NAME, UserFilter.LAST_NAME)) {
        } else if (filter.hasKeys(UserFilter.FIRST_NAME, UserFilter.LAST_NAME)) {
        } else if (filter.hasKey(UserFilter.ID)) {
            User user = getById(filter.getLong(Filter.ID));
            users = Arrays.asList(user);
        } else if (filter.hasKey(UserFilter.EMAIL)) {
            users = Arrays.asList(getByEmail(filter.getValue(UserFilter.EMAIL, String.class)));
        } else if (filter.hasKey(UserFilter.FIRST_NAME)) {
            users = getByFirstName(filter.getValue(UserFilter.FIRST_NAME, String.class));
        } else if (filter.hasKey(UserFilter.LAST_NAME)) {
            users = getByLastName(filter.getValue(UserFilter.LAST_NAME, String.class));
        } else {
            users = userRepository.findAll();
        }

        LOGGER.debug("-getByFilter(), users:{}", users);
        return users;
    }

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<User> getByFilter(Filter filter, Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * @param userId
     * @return
     */
    public User getById(final Long userId) {
        LOGGER.debug("+getById({})", userId);
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NoRecordFoundException("userId:%d", userId));
        loadUserPhones(user);
        LOGGER.debug("-getById(), user: {}", user);
        return user;
    }

    /**
     * Returns the list of users by firstName.
     *
     * @param firstName
     * @return
     */
    public List<User> getByFirstName(String firstName) {
        LOGGER.debug("+getByFirstName({})", firstName);
        if (BeanUtils.isEmpty(firstName)) {
            throw new InvalidRequestException("The firstName should provide!");
        }

        List<User> users = userRepository.getByFirstName(firstName);
        users.forEach(user -> loadUserPhones(user));
        LOGGER.debug("-getByFirstName(), users:{}", users);
        return users;
    }

    /**
     * Returns the list of users by lastName.
     *
     * @param lastName
     * @return
     */
    public List<User> getByLastName(String lastName) {
        LOGGER.debug("+getByLastName({})", lastName);
        if (BeanUtils.isEmpty(lastName)) {
            throw new InvalidRequestException("The lastName should provide!");
        }

        List<User> users = userRepository.getByLastName(lastName);
        users.forEach(user -> loadUserPhones(user));
        LOGGER.debug("-getByLastName(), users:{}", users);
        return users;
    }

    /**
     * @param user
     */
    private void loadUserPhones(User user) {
        LOGGER.debug("+loadUserPhones({})", user);
        if (BeanUtils.isNotNull(user) && BeanUtils.isEmpty(user.getPhones())) {
            List<Phone> phones = phoneRepository.findByUserId(user.getId());
            user.setPhones(Sets.asSet(phones));
        }
        LOGGER.debug("-loadUserPhones({})", user);
    }

    /**
     * Returns the user by email.
     *
     * @param email
     * @return
     */
    public User getByEmail(final String email) {
        LOGGER.debug("+getByEmail({})", email);
        Objects.requireNonNull(email);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoRecordFoundException("email:%s", email));
        loadUserPhones(user);
        LOGGER.debug("-getByEmail(), user: {}", user);
        return user;
    }

    /**
     * Returns the list of users by email.
     *
     * @param emailList
     * @return
     */
    @Override
    public List<User> getByEmails(List<String> emailList) {
        LOGGER.debug("getByEmails({})", emailList);
        Objects.requireNonNull(emailList);
        return userRepository.getByEmails(emailList);
    }

    /**
     * @param pageable
     * @return
     */
    public Page<User> getByFilter(Payload payload, Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * Updates the <code>T</code> object.
     *
     * @param user
     * @return
     */
    @Override
    public User update(User user) {
        LOGGER.debug("+update({})", user);
        user = validate(Operation.UPDATE, user);
        user = userRepository.save(user);
        LOGGER.debug("-update(), user: {}", user);
        return user;
    }

    /**
     * Updates the <code>List<T></code> objects.
     *
     * @param users
     * @return
     */
    @Override
    public List<User> update(List<User> users) {
        LOGGER.debug("+update({})", users);
        if (BeanUtils.isEmpty(users)) {
            throw new InvalidRequestException("The users should provide!");
        }

        users.forEach(user -> validate(Operation.UPDATE, user));
        users = userRepository.saveAll(users);

        LOGGER.debug("-update(), users: {}", users);
        return users;
    }

    /**
     * @param userId
     */
    public User delete(Long userId) {
        LOGGER.debug("+delete({})", userId);
        Objects.requireNonNull(userId);
        User user = getById(userId);
        LOGGER.info("Deleting {}", user);
        userRepository.deleteById(userId);
        LOGGER.debug("-delete(), user: {}", user);
        return user;
    }

    /**
     * Returns the <code>Role</code> by <code>roleType</code>.
     *
     * @param roleType
     * @return
     */
    private Optional<Role> loadByRoleType(final RoleType roleType) {
        return roleRepository.findByName(roleType.name());
    }


    /**
     * @param user
     * @param roleTypes
     * @return
     */
    @Override
    public void addRoles(final User user, RoleType... roleTypes) {
        LOGGER.debug("+addRoles({}, {})", user, roleTypes);
        if (!BeanUtils.isEmpty(roleTypes)) {
            final Set<Role> userRoles = new HashSet<>();
            Arrays.asList(roleTypes).forEach(roleType -> {
                final Optional<Role> roleOptional = loadByRoleType(roleType);
                if (roleOptional.isPresent() && EntityStatus.ACTIVE == roleOptional.get().getStatus()) {
                    userRoles.add(roleOptional.get());
                }
            });

            // add roles to the user
            user.addRoles(userRoles);
        }
        LOGGER.debug("-addRoles()");
    }

    /**
     * @param user
     * @param roleTypes
     * @return
     */
    @Override
    public boolean hasRoles(final User user, final Set<RoleType> roleTypes) {
        return user.hasRoles(Role.ofRoleTypes(roleTypes));
    }

    /**
     * Refactor this code later
     */

    /**
     * Gets the specified user.
     *
     * @param email the email address identifying the user to retrieve
     * @return The database entity object for the user
     * @throws NotFoundException if the user is not found in the database
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    public User getUser(String email) throws NotFoundException {
        User user = null;
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            throw new NotFoundException(String.format(Constants.ERR_USER_EMAIL_NOT_FOUND, email));
        }
        user = userOptional.get();

        // load user's phone numbers
        if (BeanUtils.isEmpty(user.getPhones())) {
            List<Phone> phones = phoneRepository.findByUserId(user.getId());
            user.setPhones(Sets.asSet(phones));
        }

        return userOptional.get();
    }

    /**
     * Adds a new user.
     *
     * @param email        the email address identifying the user to add
     * @param firstName    the first name of the user
     * @param lastName     the last name of the user
     * @param phoneNumbers an array of phone numbers for the user
     * @return the email of the added user
     * @throws UserAlreadyExistsException if the user's email already exists in the database
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public User addUser(String email, String password, String firstName, String lastName, List<String> phoneNumbers)
        throws UserAlreadyExistsException {
        // first check if it already exists
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException(String.format(Constants.ERR_USER_ALREADY_EXISTS, email));
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        // save user
        user = userRepository.save(user);

        /*
         * Not using this relationship here <code>https://www.baeldung.com/jpa-one-to-one</code>
         */
        final Long userId = user.getId();
        // save phones, if available
        if (BeanUtils.isNotEmpty(phoneNumbers)) {
            Set<Phone>
                phoneSet =
                phoneNumbers.stream().map(phoneNumber -> new Phone(userId, phoneNumber)).collect(Collectors.toSet());
            user.setPhones(phoneSet);

            List<Phone> phones = phoneRepository.saveAll(user.getPhones());
            user.setPhones(Sets.asSet(phones));
        }

        return user;
    }

    /**
     * Deletes the specified user.
     *
     * @param email the email of the user to delete
     * @throws NotFoundException if the user is not found in the database
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(String email) throws NotFoundException {
        // fetch it to make sure it's there
        User user = getUser(email);
        phoneRepository.deleteByUserId(user.getId());
        userRepository.delete(user);
    }
}
