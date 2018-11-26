package cz.hartrik.pia.service

import cz.hartrik.pia.dto.Account
import cz.hartrik.pia.dto.Currency
import cz.hartrik.pia.dto.Transaction
import cz.hartrik.pia.dto.User

import java.time.ZonedDateTime

/**
 *
 * @version 2018-11-26
 * @author Patrik Harag
 */
interface AuthorizedAccountManager {

    /**
     * Creates a new account.
     *
     * @param currency currency
     * @param user owner
     * @return new account
     */
    Account createAccount(Currency currency, User user)

    /**
     * Retrieves an account.
     *
     * @param id
     * @return
     */
    Account retrieveAccount(int id)

    /**
     * Creates a new transaction.
     *
     * @param sender sender
     * @param receiver receiver
     * @param amount money in receiver's currency, amount >= 0
     * @param date date
     * @param description description or null
     * @return transaction
     */
    Transaction performTransaction(Account sender, Account receiver, BigDecimal amount,
                                   ZonedDateTime date, String description)

    /**
     * Creates a new transaction.
     *
     * @param sender sender
     * @param receiver receiver
     * @param amount money in receiver's currency, amount >= 0
     * @param date date
     * @param description description or null
     * @return transaction
     */
    Transaction performTransaction(String sender, Account receiver, BigDecimal amount,
                                   ZonedDateTime date, String description)

    /**
     * Creates a new transaction.
     *
     * @param sender sender
     * @param receiver receiver
     * @param amount money in sender's currency, amount >= 0
     * @param date date
     * @param description description or null
     * @return transaction
     */
    Transaction performTransaction(Account sender, String receiver, BigDecimal amount,
                                   ZonedDateTime date, String description)

}
