package cz.hartrik.pia.controller

import cz.hartrik.pia.model.User
import org.springframework.ui.Model

import javax.servlet.http.HttpServletRequest

/**
 *
 * @version 2018-11-25
 * @author Patrik Harag
 */
class ControllerUtils {

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

}
