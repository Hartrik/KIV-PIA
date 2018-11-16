package cz.hartrik.pia.controller

import cz.hartrik.pia.dao.UserDao
import cz.hartrik.pia.dto.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Manage controller.
 *
 * @version 2018-11-16
 * @author Patrik Harag
 */
@Controller
class ServicePagesController {

    @Autowired
    private UserDao userDao

    @RequestMapping("/service/user-management")
    String pricingHandler(Model model) {
        fillAttributes(model)
        return "user-management"
    }

    private void fillAttributes(Model model) {
        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        if (auth instanceof User) {
            model.addAttribute("user", auth)
            model.addAttribute("users", userDao.getAll().findAll { it.role == User.ROLE_CUSTOMER })
            model.addAttribute("admins", userDao.getAll().findAll { it.role == User.ROLE_ADMIN })
        }
    }

}
