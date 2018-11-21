package cz.hartrik.pia.service

import cz.hartrik.pia.dto.Account
import cz.hartrik.pia.dto.User
import cz.hartrik.pia.dto.Currency

/**
 *
 * @version 2018-11-21
 * @author Patrik Harag
 */
interface AccountManager {

    Account createAccount(Currency currency, User user)

}
