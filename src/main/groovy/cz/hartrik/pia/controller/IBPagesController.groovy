package cz.hartrik.pia.controller

import cz.hartrik.pia.dao.AccountDao
import cz.hartrik.pia.dao.UserDao
import cz.hartrik.pia.dto.Currency
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Internet banking pages controller.
 *
 * @version 2018-11-21
 * @author Patrik Harag
 */
@Controller
@RequestMapping("/ib")
class IBPagesController {

    @Autowired
    private UserDao userDao

    @Autowired
    private AccountDao accountDao

    @RequestMapping("edit-user")
    String editUserHandler(Model model) {
        ControllerUtils.fillLayoutAttributes(model)
        model.addAttribute("default", ControllerUtils.getUser())
        return "edit-user"
    }

    @RequestMapping("accounts-overview")
    String accountsOverviewHandler(Model model) {
        ControllerUtils.fillLayoutAttributes(model)
        model.addAttribute('currencies', Currency.values()*.name())
        model.addAttribute('accounts', accountDao.getAll())  // TODO: nespojí se to s uživatelem
        return "accounts-overview"
    }

}
