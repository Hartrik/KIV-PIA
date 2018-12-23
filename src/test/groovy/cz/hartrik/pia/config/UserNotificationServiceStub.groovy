package cz.hartrik.pia.config

import cz.hartrik.pia.model.Account
import cz.hartrik.pia.model.Transaction
import cz.hartrik.pia.model.User
import cz.hartrik.pia.service.UserNotificationService
import org.springframework.stereotype.Service

import java.time.ZonedDateTime

/**
 *
 * @version 2018-12-23
 * @author Patrik Harag
 */
@Service
class UserNotificationServiceStub implements UserNotificationService {

    @Override
    void sendWelcome(User newUser, String rawPassword) {
    }

    @Override
    void sendStatement(User user, Account account, List<Transaction> transactions,
                       ZonedDateTime from, ZonedDateTime to) {

        throw new UnsupportedOperationException()
    }

}
