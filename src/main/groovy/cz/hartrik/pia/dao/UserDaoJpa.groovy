package cz.hartrik.pia.dao

import cz.hartrik.pia.dto.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Repository

/**
 *
 * @version 2018-11-17
 * @author Patrik Harag
 */
@Repository
class UserDaoJpa extends GenericDaoJpa<User, String> implements UserDao {

    UserDaoJpa() {
        super(User)
    }

    // UserDetailsService

    @Override
    UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        def user = getByAttribute("login", login)
        if (!user)
            throw new UsernameNotFoundException('User not found')
        return user
    }
}