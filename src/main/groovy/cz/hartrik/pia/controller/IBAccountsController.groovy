package cz.hartrik.pia.controller

import cz.hartrik.pia.WrongInputException
import cz.hartrik.pia.model.Currency
import cz.hartrik.pia.model.TransactionDraft
import cz.hartrik.pia.service.AccountManager
import cz.hartrik.pia.service.CurrencyConverter
import cz.hartrik.pia.service.TuringTestService
import cz.hartrik.pia.service.UserManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

import javax.servlet.http.HttpServletRequest
import java.time.LocalDate
import java.time.ZonedDateTime

/**
 * Internet banking pages controller.
 *
 * @version 2018-12-21
 * @author Patrik Harag
 */
@Controller
@RequestMapping("/ib")
class IBAccountsController {

    private final def pagination = new PaginationHelper([25, 50, 100, 200], 25)

    @Autowired
    private UserManager userManager

    @Autowired
    private AccountManager accountManager

    @Autowired
    private TuringTestService turingTestService

    @Autowired
    private CurrencyConverter currencyConverter

    @RequestMapping("accounts-overview")
    String accountsOverviewHandler(Model model) {
        def user = userManager.retrieveCurrentUser()
        ControllerUtils.fillLayoutAttributes(model, user)
        model.addAttribute('currencies', Currency.values()*.name())
        model.addAttribute('accounts', user.accounts)
        return "accounts-overview"
    }

    @RequestMapping(path = "create-account/action", method = RequestMethod.POST)
    String createAccountHandler(HttpServletRequest request, @RequestParam Currency currency) {
        def user = userManager.retrieveCurrentUser()
        accountManager.authorize(user) {
            createAccount(currency, user)
        }

        return ControllerUtils.redirectBack(request)
    }

    @RequestMapping("account/{id}")
    String accountHandler(Model model, @PathVariable Integer id,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer count) {

        def user = userManager.retrieveCurrentUser()
        def account = accountManager.authorize(user) {
            retrieveAccount(id)
        }

        ControllerUtils.fillLayoutAttributes(model, user)
        model.addAttribute('account', account)

        def transactions = accountManager.authorize(user) { findAllTransactionsByAccount(account) }
        def transactionsView = pagination.paginate(
                model, "/ib/account/${id}", transactions, page, count)
        model.addAttribute('transactions', transactionsView)

        return "account"
    }

    @RequestMapping("account/{id}/send")
    String sendHandler(Model model, @PathVariable Integer id) {
        def user = userManager.retrieveCurrentUser()
        def account = accountManager.authorize(user) {
            retrieveAccount(id)
        }

        ControllerUtils.fillLayoutAttributes(model, user)
        model.addAttribute('account', account)
        model.addAttribute('currencies', Currency.values()*.name())
        model.addAttribute('turing_test', turingTestService.randomTest())
        model.addAttribute('default', [date: LocalDate.now()])

        return "send-payment"
    }

    @RequestMapping(path = "account/{id}/send/action", method = RequestMethod.POST)
    String sendActionHandler(HttpServletRequest request,
            @PathVariable Integer id, TransactionDraft transactionDraft) {

        def test = turingTestService.testAnswer(
                transactionDraft.turingTestQuestionId, transactionDraft.turingTestAnswer)
        if (test == null) {
            throw new WrongInputException("Anti-robot test expired")
        } else if (!test) {
            throw new WrongInputException("Incorrect anti-robot test answer")
        }

        def user = userManager.retrieveCurrentUser()
        def account = accountManager.authorize(user) { retrieveAccount(id) }
        def amount = currencyConverter.convert(
                transactionDraft.amount, transactionDraft.currency, account.currency)

        def date = transactionDraft.parseDate()
        date = ZonedDateTime.now()  // TODO: odložené vykonání?

        accountManager.authorize(user) {
            performTransaction(account, transactionDraft.accountNumberFull, amount, date,
                    transactionDraft.description)
        }

        return ControllerUtils.redirect(request, "/ib/account/${id}")
    }

}
