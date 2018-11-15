package cz.hartrik.pia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Index controller.
 *
 * @version 2017-04-14
 * @author Patrik Harag
 */
@Controller
public class IndexController {

    @RequestMapping({"", "/index"})
    public String indexHandler(Model model) {
        return "index";
    }

}
