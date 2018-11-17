package cz.hartrik.pia.controller

import cz.hartrik.pia.dao.UserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Internet banking pages controller.
 *
 * @version 2018-11-17
 * @author Patrik Harag
 */
@Controller
@RequestMapping("/ib")
class IBPagesController {

    @Autowired
    private UserDao userDao

    @RequestMapping("edit-user")
    String createUserHandler(Model model) {
        ControllerUtils.fillLayoutAttributes(model)
        model.addAttribute("default", ControllerUtils.getUser())
        return "edit-user"
    }

}
