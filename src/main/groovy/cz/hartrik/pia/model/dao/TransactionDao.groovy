package cz.hartrik.pia.model.dao

import cz.hartrik.pia.model.Account
import cz.hartrik.pia.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import java.time.ZonedDateTime

/**
 * Transaction data access object.
 *
 * @version 2018-12-23
 * @author Patrik Harag
 */
@Repository
interface TransactionDao extends JpaRepository<Transaction, Integer> {

    @Query("""
        SELECT t FROM Transaction t
        WHERE t.sender = :account OR t.receiver = :account
        ORDER BY t.date DESC
    """)
    List<Transaction> findAllByAccount(@Param("account") Account account)

    @Query("""
        SELECT t FROM Transaction t
        WHERE
                (t.sender = :account OR t.receiver = :account)
            AND
                (t.date BETWEEN :startDate AND :endDate)
        ORDER BY t.date DESC
    """)
    List<Transaction> findAllByAccount(@Param("account") Account account,
                                       @Param("startDate") ZonedDateTime startDate,
                                       @Param("endDate") ZonedDateTime endDate)

}
