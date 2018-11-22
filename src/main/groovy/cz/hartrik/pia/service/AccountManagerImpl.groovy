package cz.hartrik.pia.service

import cz.hartrik.pia.dao.AccountDao
import cz.hartrik.pia.dto.Account
import cz.hartrik.pia.dto.Currency
import cz.hartrik.pia.dto.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.transaction.Transactional

/**
 *
 * @version 2018-11-22
 * @author Patrik Harag
 */
@Transactional
@Service
class AccountManagerImpl implements AccountManager {

    @Autowired
    AccountDao accountDao

    @Override
    Account createAccount(Currency currency, User user) {
        final def dummyNumber = "0"

        def account = new Account(owner: user, currency: currency, balance: BigDecimal.ZERO,
                accountNumber: dummyNumber, cardNumber: dummyNumber)

        account = accountDao.save(account)

        account.accountNumber = String.format('6432%06d', account.id)
        account.cardNumber = String.format('1282560512%06d', account.id)

        // TODO: asi se to nespojí s uživatelem

        return accountDao.save(account)
    }

}
