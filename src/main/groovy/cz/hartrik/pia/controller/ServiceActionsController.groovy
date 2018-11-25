package cz.hartrik.pia.controller

import cz.hartrik.pia.service.UserManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

import javax.servlet.http.HttpServletRequest

/**
 * Service actions controller.
 *
 * @version 2018-11-22
 * @author Patrik Harag
 */
@Controller
@RequestMapping('/service/a')
class ServiceActionsController {

    @Autowired
    private UserManager userManager

    @RequestMapping('remove-user')
    String removeUserHandler(HttpServletRequest request, @RequestParam Integer id) {
        def user = userManager.retrieveCurrentUser()
        userManager.authorize(user).remove(id)

        return ControllerUtils.redirectBack(request)
    }

}
