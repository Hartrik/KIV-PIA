package cz.hartrik.pia.dao

import cz.hartrik.pia.dto.Account
import groovy.transform.PackageScope
import org.springframework.stereotype.Repository

/**
 *
 * @version 2018-11-21
 * @author Patrik Harag
 */
@Repository
@PackageScope
class AccountDaoJpa extends GenericDaoJpa<Account, Integer> implements AccountDao {

    AccountDaoJpa() {
        super(Account)
    }

}