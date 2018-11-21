package cz.hartrik.pia.dao

import cz.hartrik.pia.ObjectNotFoundException
import cz.hartrik.pia.dto.DataTransferObject

/**
 * Base interface for DAOs, providing CRUD operations.
 *
 * @version 2018-11-17
 * @author Patrik Harag
 */
interface GenericDao<E extends DataTransferObject<PK>, PK extends Serializable> {

    /**
     * Either inserts new or updates existing instance.
     *
     * @param instance to be persisted
     * @return persisted instance
     */
    E save(E instance)

    /**
     *
     * @param id
     * @return instance with the given id or null if not found
     */
    E getById(PK id)

    /**
     * @param name
     * @param value
     * @return instance with the given id or null if not found
     */
    E getByAttribute(String name, String value)

    Collection<E> getAll()

    /**
     * Removes the given entity from persistence.
     *
     * @param id if of the entity instance
     */
    void delete(PK id) throws ObjectNotFoundException

}
