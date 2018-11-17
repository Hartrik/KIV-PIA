package cz.hartrik.pia.config

import cz.hartrik.pia.dao.UserDao
import cz.hartrik.pia.dto.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder

import javax.annotation.PostConstruct

/**
 *
 * @version 2018-11-17
 * @author Patrik Harag
 */
@Configuration
class DatabasePopulator {

    @Autowired
    UserDao userDao

    @Autowired
    PasswordEncoder encoder

    @PostConstruct
    void populateDB() {
        userDao.save(new User(id: '1', firstName: 'Alan', lastName: 'Linger',
                login: 'Admin001', email: 'alan@example.com',
                password: encoder.encode('1234'), role: User.ROLE_ADMIN))

        userDao.save(new User(id: '2', firstName: 'Brian', lastName: 'Norrell',
                login: 'User0001', email: 'brian@example.com',
                password: encoder.encode('0001'), role: User.ROLE_CUSTOMER))

        userDao.save(new User(id: '3', firstName: 'Casey', lastName: 'Veres',
                login: 'User0002', email: 'casey@example.com',
                password: encoder.encode('0002'), role: User.ROLE_CUSTOMER))
    }
}