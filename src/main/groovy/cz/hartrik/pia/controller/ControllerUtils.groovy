package cz.hartrik.pia.controller

import cz.hartrik.pia.dto.User
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.ui.Model

import javax.servlet.http.HttpServletRequest

/**
 *
 * @version 2018-11-17
 * @author Patrik Harag
 */
class ControllerUtils {

    static void fillLayoutAttributes(Model model) {
        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        if (auth instanceof User) {
            model.addAttribute("user", auth)
        }
    }

    static User getUser() {
        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        if (auth instanceof User) {
            return auth
        }
        throw new AccessDeniedException("Not logged")
    }

    static String redirectBack(HttpServletRequest request) {
        String referer = request.getHeader("Referer")
        return "redirect:" + (referer != null ? referer : "/")
    }

}
