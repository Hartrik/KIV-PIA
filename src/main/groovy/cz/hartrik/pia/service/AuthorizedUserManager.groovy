package cz.hartrik.pia.service

import cz.hartrik.pia.model.User

/**
 *
 * @version 2018-12-22
 * @author Patrik Harag
 */
interface AuthorizedUserManager {

    void remove(Integer id)

    void edit(Integer id, String firstName, String lastName, String personalNumber, String email)

    void create(String firstName, String lastName, String personalNumber, String email)

    List<User> findAllUsers()

}
