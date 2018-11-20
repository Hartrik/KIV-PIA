package cz.hartrik.pia.dao

import cz.hartrik.pia.dto.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Repository

import javax.persistence.NoResultException

/**
 *
 * @version 2018-11-17
 * @author Patrik Harag
 */
@Repository
class UserDaoJpa extends GenericDaoJpa<User, Integer> implements UserDao {

    UserDaoJpa() {
        super(User)
    }

    // UserDetailsService

    @Override
    UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            return getByAttribute("login", login)
        } catch (NoResultException e) {
            throw new UsernameNotFoundException('User not found')
        }
    }
}