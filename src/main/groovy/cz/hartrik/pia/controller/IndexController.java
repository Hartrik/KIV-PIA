package cz.hartrik.pia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Index controller.
 *
 * @version 2018-11-15
 * @author Patrik Harag
 */
@Controller
public class IndexController {

    private void fillAttributes(Model model) {
        // TODO: globálně pro každý controller
        model.addAttribute("user_name", null);
//        model.addAttribute("user_name", "Jan Novák");
        model.addAttribute("user_admin", false);
        model.addAttribute("user_customer", false);
    }

    @RequestMapping({"", "/index", "/about"})
    public String indexHandler(Model model) {
        fillAttributes(model);
        return "about";
    }


    @RequestMapping({"/pricing"})
    public String pricingHandler(Model model) {
        fillAttributes(model);
        return "pricing";
    }

    @RequestMapping({"/contact"})
    public String contactHandler(Model model) {
        fillAttributes(model);
        return "contact";
    }

}
