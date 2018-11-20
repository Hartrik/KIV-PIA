package cz.hartrik.pia.dto

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.*

/**
 *
 * @version 2018-11-20
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
    @JoinColumn(nullable = false)
    User owner

    @JoinColumn(nullable = false, unique = true)
    String accountNumber
    @JoinColumn(nullable = false, unique = true)
    String cardNumber

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 3)
    Currency currency

    @OneToMany(orphanRemoval = true, mappedBy = "sender")
    Set<Transaction> outcomingStatements
    @OneToMany(orphanRemoval = true, mappedBy = "receiver")
    Set<Transaction> incomingStatements

}
