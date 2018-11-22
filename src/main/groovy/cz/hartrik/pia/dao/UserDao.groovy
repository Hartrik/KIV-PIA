package cz.hartrik.pia.dao

import cz.hartrik.pia.dto.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * User data access object.
 *
 * @version 2018-11-22
 * @author Patrik Harag
 */
interface UserDao extends JpaRepository<User, Integer> {

    Optional<User> findByLogin(String login)

}
