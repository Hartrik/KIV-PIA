package cz.hartrik.pia.service

import cz.hartrik.pia.ObjectNotFoundException
import cz.hartrik.pia.dao.UserDao
import cz.hartrik.pia.dto.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

import javax.transaction.Transactional
import java.util.function.Supplier

/**
 *
 * @version 2018-11-23
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

    @Override
    void remove(Integer id) {
        def user = userDao.findById(id).orElseThrow(USER_NOT_FOUND)
        userDao.delete(user)
    }

    @Override
    void edit(Integer id, String firstName, String lastName, String personalNumber, String email) {
        def user = userDao.findById(id).orElseThrow(USER_NOT_FOUND)

        def currentUser = retrieveCurrentUser()
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
