package com.rslakra.microservice.yatrasuite.yatrathymeleaf.security;

import com.devamatre.appsuite.core.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Objects;

public class AuthUserDetails extends User {

    private final String email;
    private final String firstName;
    private final String lastName;

    private AuthUserDetails(Builder builder) {
        super(builder.username, builder.password, builder.authorities);
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    /**
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        if (!super.equals(other)) {
            return false;
        }

        AuthUserDetails that = (AuthUserDetails) other;
        return email.equals(that.email) && firstName.equals(that.firstName) && lastName.equals(that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, firstName, lastName);
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(AuthUserDetails.class)
            .add("email", getEmail())
            .add("firstName", getFirstName())
            .add("lastName", getLastName())
            .toString();
    }

    public static class Builder {

        private String email;
        private String firstName;
        private String lastName;
        private String username;
        private String password;
        private Collection<? extends GrantedAuthority> authorities;

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withAuthorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public AuthUserDetails build() {
            return new AuthUserDetails(this);
        }
    }
}
