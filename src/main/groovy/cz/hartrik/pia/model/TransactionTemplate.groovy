package cz.hartrik.pia.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

/**
 *
 * @version 2018-12-22
 * @author Patrik Harag
 */
@EqualsAndHashCode(excludes = 'owner')
@ToString(excludes = 'owner')
@Entity
@Table(name = 'table_transaction_template')
class TransactionTemplate implements DataTransferObject<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    Integer id

    /**
     * Template owner.
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    User owner

    /**
     * Template name.
     */
    @Column(nullable = false)
    String name

    BigDecimal amount
    Currency currency
    String accountNumber
    String bankCode
    String description

}
