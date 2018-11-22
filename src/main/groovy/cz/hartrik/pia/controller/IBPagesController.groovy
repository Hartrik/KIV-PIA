package cz.hartrik.pia.controller

import cz.hartrik.pia.dto.Currency
import cz.hartrik.pia.service.SessionUtils
import cz.hartrik.pia.service.UserManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

import javax.transaction.Transactional

/**
 * Internet banking pages controller.
 *
 * @version 2018-11-22
 * @author Patrik Harag
 */
@Controller
@RequestMapping("/ib")
class IBPagesController {

    @Autowired
    private UserManager userManager

    @RequestMapping("edit-user")
    String editUserHandler(Model model) {
        ControllerUtils.fillLayoutAttributes(model)
        model.addAttribute("default", SessionUtils.getUser())
        return "edit-user"
    }

    @Transactional
    @RequestMapping("accounts-overview")
    String accountsOverviewHandler(Model model) {
        ControllerUtils.fillLayoutAttributes(model)

        model.addAttribute('currencies', Currency.values()*.name())

        def user = userManager.getUpdatedAndFullyLoadedUser()
        model.addAttribute('accounts', user.accounts)

        return "accounts-overview"
    }

}
