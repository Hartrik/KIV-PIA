package cz.hartrik.pia.controller.dto

import cz.hartrik.pia.model.Currency
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Transient

/**
 *
 * @version 2018-12-23
 * @author Patrik Harag
 */
@EqualsAndHashCode
@ToString
class TransactionDraft {

    Integer amount
    Currency currency
    String accountNumber
    String bankCode
    String date
    String description

    String turingTestQuestionId
    String turingTestAnswer

    @Transient
    String getAccountNumberFull() {
        return accountNumber + "/" + bankCode
    }

}
