package cz.hartrik.pia.service

import cz.hartrik.pia.model.*

/**
 *
 * @version 2018-12-22
 * @author Patrik Harag
 */
interface AuthorizedTemplateManager {

    TransactionTemplate createTemplate(User user, String name, BigDecimal amount, Currency currency,
                                       String accountNumber, String bankCode, String description)

    TransactionTemplate editTemplate(Integer id, String name, BigDecimal amount, Currency currency,
                                       String accountNumber, String bankCode, String description)

    List<TransactionTemplate> findAllTemplatesByOwner(User owner)

    void remove(Integer id)

    TransactionTemplate findById(Integer id)

}
