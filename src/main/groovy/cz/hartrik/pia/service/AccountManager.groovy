package cz.hartrik.pia.service

import cz.hartrik.pia.model.User

/**
 *
 * @version 2018-11-26
 * @author Patrik Harag
 */
interface AccountManager {

    AuthorizedAccountManager authorize(User user)

}
