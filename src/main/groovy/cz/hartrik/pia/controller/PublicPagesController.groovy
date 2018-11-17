package cz.hartrik.pia.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Index controller.
 *
 * @version 2018-11-17
 * @author Patrik Harag
 */
@Controller
class PublicPagesController {

    @RequestMapping(["", "/index", "/about"])
    String indexHandler(Model model) {
        Utils.fillLayoutAttributes(model)
        return "about"
    }

    @RequestMapping("/login-failed")
    String loginFailedHandler(Model model) {
        Utils.fillLayoutAttributes(model)
        model.addAttribute("error", "Wrong username or password")
        return "about"
    }

    @RequestMapping("/pricing")
    String pricingHandler(Model model) {
        Utils.fillLayoutAttributes(model)
        return "pricing"
    }

    @RequestMapping("/contact")
    String contactHandler(Model model) {
        Utils.fillLayoutAttributes(model)
        return "contact"
    }

}
