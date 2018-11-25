package cz.hartrik.pia.controller

import cz.hartrik.pia.dao.TransactionDao
import cz.hartrik.pia.dto.Currency
import cz.hartrik.pia.service.AccountManager
import cz.hartrik.pia.service.UserManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * Internet banking pages controller.
 *
 * @version 2018-11-25
 * @author Patrik Harag
 */
@Controller
@RequestMapping("/ib")
class IBPagesController {

    private final def pagination = new PaginationHelper([25, 50, 100, 200], 25)

    @Autowired
    private UserManager userManager

    @Autowired
    private AccountManager accountManager

    @Autowired
    private TransactionDao transactionDao

    @RequestMapping("edit-user")
    String editUserHandler(Model model) {
        def user = userManager.retrieveCurrentUser()
        ControllerUtils.fillLayoutAttributes(model, user)
        model.addAttribute("default", user)
        return "edit-user"
    }

    @RequestMapping("accounts-overview")
    String accountsOverviewHandler(Model model) {
        def user = userManager.retrieveCurrentUser()
        ControllerUtils.fillLayoutAttributes(model, user)
        model.addAttribute('currencies', Currency.values()*.name())
        model.addAttribute('accounts', user.accounts)
        return "accounts-overview"
    }

    @RequestMapping("account/{id}")
    String accountHandler(Model model, @PathVariable Integer id,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer count) {

        def user = userManager.retrieveCurrentUser()
        def account = accountManager.authorize(user).retrieveAccount(id)

        ControllerUtils.fillLayoutAttributes(model, user)
        model.addAttribute('account', account)

        def transactions = transactionDao.findAllByAccount(account)
        def transactionsView = pagination.paginate(model, "/ib/account/${id}", transactions, page, count)
        model.addAttribute('transactions', transactionsView)

        return "account"
    }

    @RequestMapping("account/{id}/send")
    String accountHandler(Model model, @PathVariable Integer id) {
        def user = userManager.retrieveCurrentUser()
        def account = accountManager.authorize(user).retrieveAccount(id)

        ControllerUtils.fillLayoutAttributes(model, user)
        model.addAttribute('account', account)

        return "send-payment"
    }

}
