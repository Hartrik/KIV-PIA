package cz.hartrik.pia.service

import cz.hartrik.pia.dto.User

/**
 *
 * @version 2018-11-25
 * @author Patrik Harag
 */
interface UserManager {

    AuthorizedUserManager authorize(User user)

    User retrieveCurrentUser()
    User retrieveCurrentUserOrNull()

    interface AuthorizedUserManager {
        void remove(Integer id)

        void edit(Integer id, String firstName, String lastName, String personalNumber, String email)
    }

}
