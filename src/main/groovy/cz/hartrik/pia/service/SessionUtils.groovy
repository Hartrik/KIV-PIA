package cz.hartrik.pia.service

import cz.hartrik.pia.dto.User
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.context.SecurityContextHolder

/**
 *
 * @version 2018-11-22
 * @author Patrik Harag
 */
class SessionUtils {

    static User getUser() {
        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        if (auth instanceof User) {
            return auth
        }
        throw new AccessDeniedException("Not logged")
    }

}
