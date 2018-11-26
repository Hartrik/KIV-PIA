package cz.hartrik.pia.service

/**
 *
 * @version 2018-11-26
 * @author Patrik Harag
 */
interface AuthorizedUserManager {
    void remove(Integer id)

    void edit(Integer id, String firstName, String lastName, String personalNumber, String email)
}
