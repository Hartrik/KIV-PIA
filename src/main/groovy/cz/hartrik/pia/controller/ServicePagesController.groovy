package cz.hartrik.pia.controller

import cz.hartrik.pia.dao.UserDao
import cz.hartrik.pia.dto.User
import cz.hartrik.pia.service.UserManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Service pages controller.
 *
 * @version 2018-11-22
 * @author Patrik Harag
 */
@Controller
@RequestMapping("/service")
class ServicePagesController {

    @Autowired
    private UserManager userManager

    @Autowired
    private UserDao userDao

    @RequestMapping("user-management")
    String userManagementHandler(Model model) {
        def user = userManager.retrieveCurrentUser()
        ControllerUtils.fillLayoutAttributes(model, user)
        def listOfUsers = userDao.findAll()
        model.addAttribute("users", listOfUsers.findAll { it.role == User.ROLE_CUSTOMER })
        model.addAttribute("admins", listOfUsers.findAll { it.role == User.ROLE_ADMIN })
        return "user-management"
    }

    @RequestMapping("create-user")
    String createUserHandler(Model model) {
        def user = userManager.retrieveCurrentUser()
        ControllerUtils.fillLayoutAttributes(model, user)
        return "create-user"
    }

}
