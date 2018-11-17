package cz.hartrik.pia.controller

import cz.hartrik.pia.dao.UserDao
import cz.hartrik.pia.dto.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Manage controller.
 *
 * @version 2018-11-17
 * @author Patrik Harag
 */
@Controller
@RequestMapping("/service")
class ServicePagesController {

    @Autowired
    private UserDao userDao

    @RequestMapping("user-management")
    String pricingHandler(Model model) {
        Utils.fillLayoutAttributes(model)
        model.addAttribute("users", userDao.getAll().findAll { it.role == User.ROLE_CUSTOMER })
        model.addAttribute("admins", userDao.getAll().findAll { it.role == User.ROLE_ADMIN })
        return "user-management"
    }

    @RequestMapping("create-user")
    String createUserHandler(Model model) {
        Utils.fillLayoutAttributes(model)
        return "create-user"
    }

}
