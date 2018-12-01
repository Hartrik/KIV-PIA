package cz.hartrik.pia.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import javax.persistence.*

/**
 *
 * @version 2018-11-25
 * @author Patrik Harag
 */
@EqualsAndHashCode(excludes = 'accounts')
@ToString(excludes = 'accounts')
@Entity
@Table(name = 'table_user')
class User implements UserDetails, DataTransferObject<Integer> {

    static final String ROLE_CUSTOMER = 'CUSTOMER'
    static final String ROLE_ADMIN = 'ADMIN'


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    Integer id

    @Column(nullable = false)
    Boolean enabled = true

    @Column(nullable = false)
    String role

    @Column(nullable = false)
    String firstName
    @Column(nullable = false)
    String lastName
    @Column(nullable = false)
    String personalNumber
    @Column(nullable = false)
    String email

    @Column(nullable = false, unique = true)
    String login  // code
    @Column(nullable = false)
    String password   // password

    @OneToMany(mappedBy = 'owner', fetch = FetchType.EAGER /* they are listed in the menu */)
    Set<Account> accounts

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
        return enabled
    }

}