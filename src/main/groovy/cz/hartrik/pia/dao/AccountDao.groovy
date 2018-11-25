package cz.hartrik.pia.dao

import cz.hartrik.pia.dto.Account
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

    Optional<Account> findByAccountNumber(String accountNumber)

}
