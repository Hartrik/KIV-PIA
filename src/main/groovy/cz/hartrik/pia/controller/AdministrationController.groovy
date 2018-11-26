package cz.hartrik.pia.controller

import cz.hartrik.pia.dao.UserDao
import cz.hartrik.pia.dto.User
import cz.hartrik.pia.service.UserManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

import javax.servlet.http.HttpServletRequest

/**
 * Administration pages controller.
 *
 * @version 2018-11-26
 * @author Patrik Harag
 */
@Controller
@RequestMapping("/service")
class AdministrationController {

    @Autowired
    private UserManager userManager

    @Autowired
    private UserDao userDao

    @RequestMapping("user-management")
    String userManagementHandler(Model model) {
        def user = userManager.retrieveCurrentUser()
        ControllerUtils.fillLayoutAttributes(model, user)
        def listOfUsers = userDao.findAll()
        model.addAttribute("users",
                listOfUsers.findAll { it.enabled && it.role == User.ROLE_CUSTOMER })
        model.addAttribute("admins",
                listOfUsers.findAll { it.enabled && it.role == User.ROLE_ADMIN })
        return "user-management"
    }

    @RequestMapping("create-user")
    String createUserHandler(Model model) {
        def user = userManager.retrieveCurrentUser()
        ControllerUtils.fillLayoutAttributes(model, user)
        return "create-user"
    }

    @RequestMapping('user/{id}/remove/action')
    String removeUserHandler(HttpServletRequest request, @PathVariable Integer id) {
        def user = userManager.retrieveCurrentUser()
        userManager.authorize(user).remove(id)

        return ControllerUtils.redirectBack(request)
    }

}
