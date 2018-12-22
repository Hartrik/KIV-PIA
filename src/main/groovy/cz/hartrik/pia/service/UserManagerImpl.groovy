package cz.hartrik.pia.service

import cz.hartrik.pia.JavaBank
import cz.hartrik.pia.ObjectNotFoundException
import cz.hartrik.pia.WrongInputException
import cz.hartrik.pia.model.User
import cz.hartrik.pia.model.dao.UserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

import javax.transaction.Transactional
import java.util.function.Supplier

/**
 *
 * @version 2018-12-22
 * @author Patrik Harag
 */
@Transactional
@Service
class UserManagerImpl implements UserManager {

    private static final Supplier USER_NOT_FOUND = {
        new ObjectNotFoundException("User not found!")
    }

    @Autowired
    private UserDao userDao

    @Autowired
    private PasswordEncoder encoder

    @Autowired
    private UserNotificationService notificationService

    @Override
    <T> T authorize(User user, @DelegatesTo(AuthorizedUserManager) Closure<T> transaction) {
        transaction.delegate = new AuthorizedUserManagerImpl(user)
        transaction()
    }

    @Override
    <T> T authorizeWithCurrentUser(@DelegatesTo(AuthorizedUserManager) Closure<T> transaction) {
        transaction.delegate = new AuthorizedUserManagerImpl(retrieveCurrentUser())
        transaction()
    }

    private class AuthorizedUserManagerImpl implements AuthorizedUserManager {

        private final User currentUser

        AuthorizedUserManagerImpl(User currentUser) {
            this.currentUser = currentUser
        }

        @Override
        void remove(Integer id) {
            if (currentUser.role != User.ROLE_ADMIN) {
                throw new AccessDeniedException("Only admin can remove a user")
            }

            def user = userDao.findById(id).orElseThrow(USER_NOT_FOUND)
            if (user.role == User.ROLE_ADMIN) {
                throw new AccessDeniedException("Cannot remove admin's account")
            }

            user.enabled = false
            userDao.save(user)
        }

        @Override
        void edit(Integer id, String firstName, String lastName, String personalNumber, String email) {
            def user = userDao.findById(id).orElseThrow(USER_NOT_FOUND)

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
        }

        @Override
        void create(String firstName, String lastName, String personalNumber, String email) {
            if (currentUser.role != User.ROLE_ADMIN) {
                throw new AccessDeniedException("Users are not allowed to create new accounts")
            }

            if (!firstName || !lastName || !personalNumber || !email) {
                throw new WrongInputException("Mandatory parameter not set!")
            }

            def rawPassword = JavaBank.generateRandomPassword(new Random())
            def user = userDao.save(new User(
                    role: User.ROLE_CUSTOMER,
                    firstName: firstName,
                    lastName: lastName,
                    personalNumber: personalNumber,
                    email: email,
                    login: String.format("User%04d", userDao.count()),
                    password: encoder.encode(rawPassword)
            ))
            notificationService.onUserCreated(user, rawPassword)
        }

        @Override
        List<User> findAllUsers() {
            userDao.findAll()
        }
    }

    @Override
    User retrieveCurrentUser() {
        def user = retrieveCurrentUserOrNull()
        if (user) {
            return user
        } else {
            throw USER_NOT_FOUND.get()
        }
    }

    @Override
    User retrieveCurrentUserOrNull() {
        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        if (auth instanceof User) {
            return userDao.findById(auth.id).orElseThrow(USER_NOT_FOUND)
        }
        return null
    }
}
