package cz.hartrik.pia.dao

import cz.hartrik.pia.dto.User
import org.springframework.security.core.userdetails.UserDetailsService

/**
 *
 * @version 2018-11-17
 * @author Patrik Harag
 */
interface UserDao extends UserDetailsService, GenericDao<User, String> {

}