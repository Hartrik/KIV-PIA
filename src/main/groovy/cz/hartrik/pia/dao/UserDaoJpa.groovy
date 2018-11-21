package cz.hartrik.pia.dao

import cz.hartrik.pia.dto.User
import groovy.transform.PackageScope
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Repository

/**
 *
 * @version 2018-11-21
 * @author Patrik Harag
 */
@Repository
@PackageScope
class UserDaoJpa extends GenericDaoJpa<User, Integer> implements UserDao {

    UserDaoJpa() {
        super(User)
    }

    // UserDetailsService

    @Override
    UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        def user = getByAttribute("login", login)
        if (user) {
            return user
        } else {
            throw new UsernameNotFoundException('User not found')
        }
    }
}