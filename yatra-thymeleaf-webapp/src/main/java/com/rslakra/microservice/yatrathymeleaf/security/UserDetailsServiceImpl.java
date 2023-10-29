package com.rslakra.microservice.yatrathymeleaf.security;

import com.rslakra.frameworks.core.BeanUtils;
import com.rslakra.frameworks.spring.exception.InvalidRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private final PasswordEncoder passwordEncoder;
    private final Map<String, AuthUserDetails> userRegistry = new HashMap<>();

    /**
     * @param passwordEncoder
     */
    public UserDetailsServiceImpl(PasswordEncoder passwordEncoder) {
        LOGGER.debug("UserDetailsServiceImpl({})", passwordEncoder);
        this.passwordEncoder = passwordEncoder;
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        LOGGER.debug("+init()");
        userRegistry.put("rlakra", new AuthUserDetails.Builder()
            .withFirstName("Rohtash")
            .withLastName("Singh")
            .withEmail("rlakra@rlakra.com")
            .withUsername("rlakra")
            .withPassword(passwordEncoder.encode("password"))
            .withAuthorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
            .build());

        userRegistry.put("rslakra", new AuthUserDetails.Builder()
            .withFirstName("Rohtash")
            .withLastName("Lakra")
            .withEmail("rslakra@lakra.com")
            .withUsername("rslakra")
            .withPassword(passwordEncoder.encode("admin"))
            .withAuthorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")))
            .build());
        LOGGER.debug("-init(),userRegistry: {}", userRegistry.size());
    }

    /**
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        LOGGER.debug("+loadUserByUsername({})", userName);
        if (BeanUtils.isEmpty(userName)) {
            throw new InvalidRequestException("The userName should provide!");
        }

        AuthUserDetails userDetails = userRegistry.get(userName);
        if (BeanUtils.isNull(userDetails)) {
            throw new UsernameNotFoundException(userName);
        }

        LOGGER.debug("-init(), userDetails: {}", userDetails);
        return userDetails;
    }
}
