package cz.hartrik.pia.model.dao

import cz.hartrik.pia.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Account data access object.
 *
 * @version 2018-11-25
 * @author Patrik Harag
 */
@Repository
interface AccountDao extends JpaRepository<Account, Integer> {

    /**
     * Returns account by its number.
     *
     * @param accountNumber account number
     * @return account
     */
    Optional<Account> findByAccountNumber(String accountNumber)

}
