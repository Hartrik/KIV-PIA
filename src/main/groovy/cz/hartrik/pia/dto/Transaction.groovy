package cz.hartrik.pia.dto

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.*
import java.time.ZonedDateTime

/**
 *
 * @version 2018-11-20
 * @author Patrik Harag
 */
@EqualsAndHashCode
@ToString
@Entity
@Table(name = 'table_statement')
class Transaction implements DataTransferObject<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    Integer id

    /**
     * The amount sent in sender's currency.
     */
    BigDecimal amountSent

    /**
     * The amount received in receiver's currency.
     */
    BigDecimal amountReceived

    /**
     * Sender's account.
     */
    @ManyToOne
    Account sender

    /**
     * Receiver's account.
     */
    @ManyToOne
    Account receiver

    @Basic
    @Column(nullable = false)
    ZonedDateTime date

    String description

}
