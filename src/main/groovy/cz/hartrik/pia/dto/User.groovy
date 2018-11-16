package cz.hartrik.pia.dto

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 *
 * @version 2018-11-16
 * @author Patrik Harag
 */
@EqualsAndHashCode
@ToString
class User implements UserDetails {

    static final String ROLE_CUSTOMER = 'CUSTOMER'
    static final String ROLE_ADMIN = 'ADMIN'


    String role = ROLE_CUSTOMER

    String firstName
    String secondName
    String phone
    String email
    String address

    String accountNumber
    String cardNumber
    String login  // code
    String password   // password

    // --- UserDetails ---
    // TODO: transient

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role))
    }

    @Override
    String getPassword() {
        return password
    }

    @Override
    String getUsername() {
        return login
    }

    @Override
    boolean isAccountNonExpired() {
        return true
    }

    @Override
    boolean isAccountNonLocked() {
        return true
    }

    @Override
    boolean isCredentialsNonExpired() {
        return true
    }

    @Override
    boolean isEnabled() {
        return true
    }
}
