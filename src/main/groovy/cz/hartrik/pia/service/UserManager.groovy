package cz.hartrik.pia.service

/**
 *
 * @version 2018-11-22
 * @author Patrik Harag
 */
interface UserManager {

    void remove(Integer id)

    void edit(Integer id, String firstName, String lastName, String personalNumber, String email)

}
