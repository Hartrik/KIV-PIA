package cz.hartrik.pia.config

import cz.hartrik.pia.dao.UserDao
import cz.hartrik.pia.dto.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder

import javax.annotation.PostConstruct

/**
 *
 * @version 2018-11-21
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
        register(new User(id: 1, firstName: 'Alan', lastName: 'Linger',
                email: 'alan@example.com', personalNumber: '123456',
                role: User.ROLE_ADMIN,
                login: 'Admin001', password: encoder.encode('1234')))

        register(new User(id: 2, firstName: 'Brian', lastName: 'Norrell',
                email: 'brian@example.com', personalNumber: '123456',
                role: User.ROLE_CUSTOMER,
                login: 'User0001', password: encoder.encode('0001')))

        register(new User(id: 3, firstName: 'Casey', lastName: 'Veres',
                email: 'casey@example.com', personalNumber: '123456',
                role: User.ROLE_CUSTOMER,
                login: 'User0002', password: encoder.encode('0002')))
    }

    private def register(User newUser) {
        if (!userDao.getByAttribute('login', newUser.login)) {
            userDao.save(newUser)
        }
    }

}