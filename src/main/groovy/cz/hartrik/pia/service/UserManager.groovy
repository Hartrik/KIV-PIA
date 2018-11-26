package cz.hartrik.pia.service

import cz.hartrik.pia.dto.User

/**
 *
 * @version 2018-11-26
 * @author Patrik Harag
 */
interface UserManager {

    AuthorizedUserManager authorize(User user)

    User retrieveCurrentUser()
    User retrieveCurrentUserOrNull()

}
