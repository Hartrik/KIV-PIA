package cz.hartrik.pia.controller.dto

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 *
 * @version 2018-12-22
 * @author Patrik Harag
 */
@EqualsAndHashCode
@ToString
class UserDraft {

    String firstName
    String lastName
    String personalNumber
    String email

    String turingTestQuestionId
    String turingTestAnswer

}
