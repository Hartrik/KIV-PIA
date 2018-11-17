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
        ControllerUtils.fillLayoutAttributes(model)
        return "about"
    }

    @RequestMapping("/login-failed")
    String loginFailedHandler(Model model) {
        ControllerUtils.fillLayoutAttributes(model)
        model.addAttribute("error", "Wrong username or password")
        return "about"
    }

    @RequestMapping("/pricing")
    String pricingHandler(Model model) {
        ControllerUtils.fillLayoutAttributes(model)
        return "pricing"
    }

    @RequestMapping("/contact")
    String contactHandler(Model model) {
        ControllerUtils.fillLayoutAttributes(model)
        return "contact"
    }

}
