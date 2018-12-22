package cz.hartrik.pia.service

import cz.hartrik.pia.model.User

/**
 *
 * @version 2018-12-22
 * @author Patrik Harag
 */
interface TemplateManager {

    def <T> T authorize(User user, @DelegatesTo(AuthorizedTemplateManager) Closure<T> transaction)

}
