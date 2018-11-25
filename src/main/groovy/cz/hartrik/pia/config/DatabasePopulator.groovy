package cz.hartrik.pia.config

import cz.hartrik.pia.dao.UserDao
import cz.hartrik.pia.dto.Account
import cz.hartrik.pia.dto.Currency
import cz.hartrik.pia.dto.User
import cz.hartrik.pia.service.AccountManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder

import javax.annotation.PostConstruct
import javax.transaction.Transactional
import java.time.ZonedDateTime

/**
 *
 * @version 2018-11-25
 * @author Patrik Harag
 */
@Configuration
class DatabasePopulator {

    @Autowired
    UserDao userDao

    @Autowired
    AccountManager accountManager

    @Autowired
    PasswordEncoder encoder

    @PostConstruct
    @Transactional
    void populateDB() {
        def admin = findOrCreate(new User(id: 1, firstName: 'Alan', lastName: 'Linger',
                email: 'alan@example.com', personalNumber: '123456',
                role: User.ROLE_ADMIN,
                login: 'Admin001', password: encoder.encode('1234')))

        def user1 = findOrCreate(new User(id: 2, firstName: 'Brian', lastName: 'Norrell',
                email: 'brian@example.com', personalNumber: '123456',
                role: User.ROLE_CUSTOMER,
                login: 'User0001', password: encoder.encode('0001')))

        def user2 = findOrCreate(new User(id: 3, firstName: 'Casey', lastName: 'Veres',
                email: 'casey@example.com', personalNumber: '123456',
                role: User.ROLE_CUSTOMER,
                login: 'User0002', password: encoder.encode('0002')))

        if (!user1.accounts && !user2.accounts) {
            def account1 = accountManager.authorize(user1).createAccount(Currency.CZK, user1)
            def account2 = accountManager.authorize(user2).createAccount(Currency.CZK, user2)

            def random = new Random(42)
            def now = ZonedDateTime.now()

            def account1Created = now.minusMinutes(360 * 120)
            accountManager.authorize(admin).performTransaction(generateAccountNumber(random), account1,
                    255000, account1Created, "Initial deposit")

            def account2Created = now.minusMinutes(720 * 5)
            accountManager.authorize(admin).performTransaction(generateAccountNumber(random), account2,
                    128000, account2Created, "Initial deposit")

            generateTransactions(accountManager.authorize(admin),
                    account1, account1Created, 360, 120, 255000/5, random)
            generateTransactions(accountManager.authorize(admin),
                    account2, account2Created, 720, 5, 128000/5, random)

            accountManager.authorize(user1)
                    .performTransaction(account1, account2, 105.50, now, 'Subscription payment')
        }
    }

    private User findOrCreate(User newUser) {
        def user = userDao.findByLogin(newUser.login)
        return user.orElseGet { userDao.save(newUser) }
    }

    private void generateTransactions(accountManager, Account account, ZonedDateTime start,
            double timeAlpha, int count, double amountAlpha, Random random) {

        ZonedDateTime currentTime = start
        for (int i = 0; i < count; i++) {
            currentTime = currentTime.plusMinutes((long) (Math.abs(random.nextGaussian() * timeAlpha)))
            def number = generateAccountNumber(random)
            double amount = BigDecimal.valueOf(Math.abs(random.nextGaussian()) * amountAlpha).round(2)
            if (random.nextBoolean()) {
                accountManager.performTransaction(number, account, amount, currentTime, null)
            } else {
                accountManager.performTransaction(account, number, amount, currentTime, null)
            }
        }
    }

    private String generateRandomNumber(Random random, int digits) {
        def chars = '0123456789'.chars
        return (0..<digits).collect { chars[random.nextInt(chars.size())] }.join('')
    }

    private String generateAccountNumber(Random random) {
        generateRandomNumber(random, 10) + '/' + generateRandomNumber(random, 4)
    }

}