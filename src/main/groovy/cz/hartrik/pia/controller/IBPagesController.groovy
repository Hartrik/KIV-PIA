package cz.hartrik.pia.controller

import cz.hartrik.pia.ObjectNotFoundException
import cz.hartrik.pia.dao.AccountDao
import cz.hartrik.pia.dto.Currency
import cz.hartrik.pia.dto.User
import cz.hartrik.pia.service.SessionUtils
import cz.hartrik.pia.service.UserManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

import javax.transaction.Transactional

/**
 * Internet banking pages controller.
 *
 * @version 2018-11-23
 * @author Patrik Harag
 */
@Controller
@RequestMapping("/ib")
class IBPagesController {

    @Autowired
    private UserManager userManager

    @Autowired
    private AccountDao accountDao

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

    @Transactional
    @RequestMapping("account/{id}")
    String accountHandler(Model model, @PathVariable Integer id) {
        ControllerUtils.fillLayoutAttributes(model)

        def account = accountDao.findById(id)
                .orElseThrow { new ObjectNotFoundException('Account not found') }
        def user = SessionUtils.getUser()

        if (user.role != User.ROLE_ADMIN && account.owner.id != user.id) {
            throw new AccessDeniedException("Cannot show other user's account")
        }

        model.addAttribute('account', account)
        return "account"
    }

}
