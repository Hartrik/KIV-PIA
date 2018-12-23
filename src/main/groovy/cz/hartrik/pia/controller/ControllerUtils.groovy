package cz.hartrik.pia.controller

import cz.hartrik.pia.WrongInputException
import cz.hartrik.pia.model.User
import org.springframework.ui.Model

import javax.servlet.http.HttpServletRequest
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 *
 * @version 2018-12-23
 * @author Patrik Harag
 */
class ControllerUtils {

    private static def ISO_8601_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    static void fillLayoutAttributes(Model model, User currentUser) {
        model.addAttribute("user", currentUser)
    }

    static String redirectBack(HttpServletRequest request) {
        String referer = request.getHeader("Referer")
        return "redirect:" + (referer != null ? referer : "/")
    }

    static String redirect(HttpServletRequest request, String page) {
        return "redirect:" + page
    }

    static ZonedDateTime parseDate(String date) {
        try {
            def localDate = LocalDate.parse(date, ISO_8601_DATE_FORMATTER)
            localDate.atStartOfDay(ZoneId.systemDefault())
        } catch (any) {
            throw new WrongInputException(any.message)
        }
    }

}
