package cz.hartrik.pia.service

import cz.hartrik.pia.model.User

/**
 *
 * @version 2018-12-01
 * @author Patrik Harag
 */
interface UserManager {

    def <T> T authorize(User user, @DelegatesTo(AuthorizedUserManager) Closure<T> transaction)
    def <T> T authorizeWithCurrentUser(@DelegatesTo(AuthorizedUserManager) Closure<T> transaction)

    User retrieveCurrentUser()
    User retrieveCurrentUserOrNull()

}
