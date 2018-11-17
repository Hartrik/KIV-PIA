package cz.hartrik.pia.controller

import cz.hartrik.pia.dao.UserDao
import cz.hartrik.pia.dto.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

import javax.servlet.http.HttpServletRequest

/**
 * Internet banking actions controller.
 *
 * @version 2018-11-17
 * @author Patrik Harag
 */
@Controller
@RequestMapping("/ib/a")
class IBActionsController {

    @Autowired
    private UserDao userDao

    @RequestMapping(path = "edit-user", method = RequestMethod.POST)
    String createUserHandler(
            HttpServletRequest request,
            @RequestParam String id,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String personalNumber,
            @RequestParam String email) {

        def user = userDao.getById(id)
        def currentUser = ControllerUtils.getUser()
        if (user.id != currentUser.id && currentUser.role == User.ROLE_CUSTOMER) {
            throw new AccessDeniedException("User cannot edit other user's account")
        } else {
            // we want to update a cached user as well
            user = currentUser
        }

        user.firstName = firstName
        user.lastName = lastName
        user.personalNumber = personalNumber
        user.email = email
        userDao.save(user)

        userDao.getAll().each {
            println it
        }

        return ControllerUtils.redirectBack(request)
    }

}
