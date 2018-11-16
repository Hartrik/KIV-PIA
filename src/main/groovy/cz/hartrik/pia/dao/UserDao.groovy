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
class UserDao implements UserDetailsService {

    private final Map<String, User> usernameIndex

    @Autowired
    UserDao(PasswordEncoder encoder) {
        this.usernameIndex = new ConcurrentHashMap<>()

        save(new User(id: '1', firstName: 'Alan', secondName: 'Linger', login: 'Admin001', email: 'alan@example.com',
                password: encoder.encode('1234'), role: User.ROLE_ADMIN))
        save(new User(id: '2', firstName: 'Brian', secondName: 'Norrell', login: 'User0001', email: 'brian@example.com',
                password: encoder.encode('0001'), role: User.ROLE_CUSTOMER))
        save(new User(id: '3', firstName: 'Casey', secondName: 'Veres', login: 'User0002', email: 'casey@example.com',
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

    Collection<User> getAll() {
        return usernameIndex.values()
    }

    @Override
    UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        def user = findByAccountNumber(id)
        if (!user)
            throw new UsernameNotFoundException('User not found')
        return user
    }
}