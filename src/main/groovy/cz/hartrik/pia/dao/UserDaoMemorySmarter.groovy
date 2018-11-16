package cz.hartrik.pia.dao

import cz.hartrik.pia.dto.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository

import java.util.concurrent.ConcurrentHashMap

/**
 *
 * @version 2018-11-16
 * @author Patrik Harag
 */
@Repository
class UserDaoMemorySmarter implements UserDetailsService {

    private final Map<String, User> usernameIndex

    @Autowired
    UserDaoMemorySmarter(PasswordEncoder encoder) {
        this.usernameIndex = new ConcurrentHashMap<>()

        save(new User(firstName: 'Alan', secondName: 'Linger', login: 'Admin001',
                password: encoder.encode('1234'), role: User.ROLE_ADMIN))
        save(new User(firstName: 'Brian', secondName: 'Norrell', login: 'User0001',
                password: encoder.encode('0001'), role: User.ROLE_CUSTOMER))
        save(new User(firstName: 'Casey', secondName: 'Veres', login: 'User0002',
                password: encoder.encode('0002'), role: User.ROLE_CUSTOMER))
    }

    void save(User user) {
        usernameIndex.put(user.login, user)
    }

    void remove(User toRemove) {
        usernameIndex.remove(toRemove.getUsername())
    }

    User findByAccountNumber(String accountNumber) {
        return usernameIndex.get(accountNumber)
    }

    @Override
    UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        def user = findByAccountNumber(id)
        if (!user)
            throw new UsernameNotFoundException('User not found')
        return user
    }
}