package cz.hartrik.pia.service

import cz.hartrik.pia.model.Account
import cz.hartrik.pia.model.Transaction
import cz.hartrik.pia.model.User

import java.time.ZonedDateTime

/**
 *
 * @version 2018-12-23
 * @author Patrik Harag
 */
interface UserNotificationService {

    void sendWelcome(User newUser, String rawPassword)

    void sendStatement(User user, Account account, List<Transaction> transactions,
                       ZonedDateTime from, ZonedDateTime to)

}
