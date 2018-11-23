package cz.hartrik.pia.controller

import cz.hartrik.pia.dto.Currency
import cz.hartrik.pia.service.AccountManager

import cz.hartrik.pia.service.UserManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

import javax.servlet.http.HttpServletRequest

/**
 * Internet banking actions controller.
 *
 * @version 2018-11-22
 * @author Patrik Harag
 */
@Controller
@RequestMapping("/ib/a")
class IBActionsController {

    @Autowired
    private UserManager userManager

    @Autowired
    private AccountManager accountManager

    @RequestMapping(path = "edit-user", method = RequestMethod.POST)
    String editUserHandler(
            HttpServletRequest request,
            @RequestParam Integer id,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String personalNumber,
            @RequestParam String email) {

        userManager.edit(id, firstName, lastName, personalNumber, email)

        return ControllerUtils.redirectBack(request)
    }

    @RequestMapping(path = "create-account", method = RequestMethod.POST)
    String createAccountHandler(HttpServletRequest request, @RequestParam Currency currency) {
        accountManager.createAccount(currency, userManager.retrieveCurrentUser())

        return ControllerUtils.redirectBack(request)
    }

}
