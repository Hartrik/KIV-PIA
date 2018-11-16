package cz.hartrik.pia.controller

import cz.hartrik.pia.dto.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Index controller.
 *
 * @version 2018-11-16
 * @author Patrik Harag
 */
@Controller
class PublicPagesController {

    private void fillAttributes(Model model) {
        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        if (auth instanceof User) {
            model.addAttribute("user", auth)
        }
    }

    @RequestMapping(["", "/index", "/about"])
    String indexHandler(Model model) {
        fillAttributes(model)
        return "about"
    }

    @RequestMapping("/pricing")
    String pricingHandler(Model model) {
        fillAttributes(model)
        return "pricing"
    }

    @RequestMapping("/contact")
    String contactHandler(Model model) {
        fillAttributes(model)
        return "contact"
    }

}
