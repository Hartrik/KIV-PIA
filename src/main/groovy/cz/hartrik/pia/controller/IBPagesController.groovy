package cz.hartrik.pia.controller

import cz.hartrik.pia.ObjectNotFoundException
import cz.hartrik.pia.dao.AccountDao
import cz.hartrik.pia.dao.TransactionDao
import cz.hartrik.pia.dto.Currency
import cz.hartrik.pia.dto.User

import cz.hartrik.pia.service.UserManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

import javax.transaction.Transactional

/**
 * Internet banking pages controller.
 *
 * @version 2018-11-25
 * @author Patrik Harag
 */
@Controller
@RequestMapping("/ib")
class IBPagesController {

    private static def PAGINATION_VALUES = [25, 50, 100, 200]
    private static def DEFAULT_PAGINATION = PAGINATION_VALUES[0]

    @Autowired
    private UserManager userManager

    @Autowired
    private AccountDao accountDao

    @Autowired
    private TransactionDao transactionDao

    @RequestMapping("edit-user")
    String editUserHandler(Model model) {
        def user = userManager.retrieveCurrentUser()
        ControllerUtils.fillLayoutAttributes(model, user)
        model.addAttribute("default", user)
        return "edit-user"
    }

    @Transactional
    @RequestMapping("accounts-overview")
    String accountsOverviewHandler(Model model) {
        def user = userManager.retrieveCurrentUser()
        ControllerUtils.fillLayoutAttributes(model, user)
        model.addAttribute('currencies', Currency.values()*.name())
        model.addAttribute('accounts', user.accounts)
        return "accounts-overview"
    }

    @Transactional
    @RequestMapping("account/{id}")
    String accountHandler(Model model, @PathVariable Integer id,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer count) {

        def user = userManager.retrieveCurrentUser()
        def account = accountDao.findById(id)
                .orElseThrow { new ObjectNotFoundException('Account not found') }
        if (user.role != User.ROLE_ADMIN && account.owner.id != user.id) {
            throw new AccessDeniedException("Cannot show other user's account")
        }

        ControllerUtils.fillLayoutAttributes(model, user)
        model.addAttribute('account', account)

        def transactions = transactionDao.findAllByAccount(account)
        count = (!count || count < 1) ? DEFAULT_PAGINATION : count
        page = (!page || page < 0) ? 0 : page
        int pages = (transactions.size() + count - 1) / count
        if (page >= pages) page = pages - 1
        def transactionsView = transactions.subList(
                Math.min(page * count, transactions.size()),
                Math.min((page + 1) * count, transactions.size())
        )
        model.addAttribute('transactions', transactionsView)

        def pageUrls = (0..<pages).collect { "/ib/account/${id}?page=$it&count=$count" }
        model.addAttribute('pagination_pages', pageUrls)
        model.addAttribute('pagination_current_page', pageUrls[page])
        def countUrls = PAGINATION_VALUES.collect { [it, "/ib/account/${id}?page=0&count=$it"] }
        model.addAttribute('pagination_count', countUrls)
        model.addAttribute('pagination_current_count', countUrls[PAGINATION_VALUES.indexOf(count)])
        return "account"
    }

}
