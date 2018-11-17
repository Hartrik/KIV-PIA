package cz.hartrik.pia.controller

import cz.hartrik.pia.dto.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.ui.Model

/**
 *
 * @version 2018-11-17
 * @author Patrik Harag
 */
class Utils {

    static void fillLayoutAttributes(Model model) {
        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        if (auth instanceof User) {
            model.addAttribute("user", auth)
        }
    }

}
