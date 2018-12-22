package cz.hartrik.pia.controller.dto

import cz.hartrik.pia.WrongInputException
import cz.hartrik.pia.model.Currency
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Transient
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 *
 * @version 2018-12-22
 * @author Patrik Harag
 */
@EqualsAndHashCode
@ToString
class TransactionDraft {

    private static def ISO_8601_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd")


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

    @Transient
    ZonedDateTime parseDate() {
        try {
            def localDate = LocalDate.parse(date, ISO_8601_DATE_FORMATTER)
            localDate.atStartOfDay(ZoneId.systemDefault())
        } catch (any) {
            throw new WrongInputException(any.message)
        }
    }

}
