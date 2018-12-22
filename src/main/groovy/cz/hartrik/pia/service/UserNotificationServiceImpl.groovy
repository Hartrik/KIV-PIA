package cz.hartrik.pia.service

import cz.hartrik.pia.model.User
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 *
 * @version 2018-12-22
 * @author Patrik Harag
 */
@Service
class UserNotificationServiceImpl implements UserNotificationService {

    private static final def LOGGER = LoggerFactory.getLogger(UserNotificationServiceImpl)

    @Override
    void onUserCreated(User newUser, String rawPassword) {
        // for testing purposes
        LOGGER.info("User created: login=${newUser.login} password=${rawPassword}")
    }
}
