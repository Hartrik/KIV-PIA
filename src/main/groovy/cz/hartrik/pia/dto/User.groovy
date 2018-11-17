package cz.hartrik.pia.dto

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Transient

/**
 *
 * @version 2018-11-17
 * @author Patrik Harag
 */
@EqualsAndHashCode
@ToString
@Entity
@Table(name = 'table_user')
class User implements UserDetails, DataTransferObject<String> {

    static final String ROLE_CUSTOMER = 'CUSTOMER'
    static final String ROLE_ADMIN = 'ADMIN'


    String role = ROLE_CUSTOMER

    @Id
    String id

    String firstName
    String lastName
    String personalNumber
    String email

    String login  // code
    String password   // password

    // UserDetails

    @Override
    @Transient
    Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role))
    }

    @Override
    @Transient
    String getPassword() {
        return password
    }

    @Override
    @Transient
    String getUsername() {
        return login
    }

    @Override
    @Transient
    boolean isAccountNonExpired() {
        return true
    }

    @Override
    @Transient
    boolean isAccountNonLocked() {
        return true
    }

    @Override
    @Transient
    boolean isCredentialsNonExpired() {
        return true
    }

    @Override
    @Transient
    boolean isEnabled() {
        return true
    }

    // DataTransferObject

    @Override
    @Transient
    String getPK() {
        return id
    }
}
