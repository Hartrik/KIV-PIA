package cz.hartrik.pia.dto

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.*

/**
 *
 * @version 2018-11-22
 * @author Patrik Harag
 */
@EqualsAndHashCode
@ToString
@Entity
@Table(name = 'table_account')
class Account implements DataTransferObject<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    Integer id

    @Column(nullable = false)
    BigDecimal balance

    @ManyToOne
    @JoinColumn
    User owner

    @Column(nullable = false, unique = true)
    String accountNumber
    @Column(nullable = false, unique = true)
    String cardNumber

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 3)
    Currency currency

    @OneToMany(mappedBy = "sender")
    Set<Transaction> outcomingStatements
    @OneToMany(mappedBy = "receiver")
    Set<Transaction> incomingStatements

}
