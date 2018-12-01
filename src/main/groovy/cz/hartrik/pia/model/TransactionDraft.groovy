package cz.hartrik.pia.model

import cz.hartrik.pia.WrongInputException

import javax.persistence.Transient
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 *
 * @version 2018-11-25
 * @author Patrik Harag
 */
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

    Integer sender

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
