package cz.hartrik.pia.dao

import cz.hartrik.pia.ObjectNotFoundException
import cz.hartrik.pia.dto.DataTransferObject

import javax.persistence.EntityManager
import javax.persistence.NoResultException
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

/**
 * JPA implementation of the GenericDao interface.
 *
 * @version 2018-11-21
 * @author Patrik Harag
 */
class GenericDaoJpa<E extends DataTransferObject<PK>, PK extends Serializable> implements GenericDao<E, PK> {

    @PersistenceContext
    protected EntityManager entityManager

    protected Class<E> persistedClass

    /**
     *
     * @param em entity manager
     * @param persistedClass entity type to be persisted by this instance
     */
    GenericDaoJpa(Class<E> persistedClass) {
        this.persistedClass = persistedClass
    }

    @Override
    @Transactional
    E save(E instance) {
        if (instance.getId() == null) {
            // pokud existuje záznam s PK, tak vyhodí chybu
            entityManager.persist(instance)
            return instance
        } else {
            // zkontroluje jestli tam není položka s PK, pokud je tak ho AKTUALIZUJE
            // -- to nechceme např při registraci uživatele
            return entityManager.merge(instance)
        }
    }

    @Override
    @Transactional
    E getById(PK id) {
        try {
            return entityManager.find(persistedClass, id)
        } catch (NoResultException e) {
            return null
        }
    }

    @Override
    E getByAttribute(String name, String value) {
        def criteriaBuilder = entityManager.getCriteriaBuilder()
        def criteriaQuery = criteriaBuilder.createQuery(persistedClass)
        def rootQuery = criteriaQuery.from(persistedClass)
        criteriaQuery.where(criteriaBuilder.equal(rootQuery.get(name), value))
        criteriaQuery.select(rootQuery)
        def query = entityManager.createQuery(criteriaQuery)

        try {
            return query.getSingleResult()
        } catch (NoResultException e) {
            return null
        }
    }

    @Override
    @Transactional
    Collection<E> getAll() {
        def criteriaBuilder = entityManager.getCriteriaBuilder()
        def criteriaQuery = criteriaBuilder.createQuery(persistedClass)
        def rootQuery = criteriaQuery.from(persistedClass)
        criteriaQuery.select(rootQuery)
        def query = entityManager.createQuery(criteriaQuery)
        return query.getResultList()
    }

    @Override
    @Transactional
    void delete(PK id) {
        E e = entityManager.find(persistedClass, id)
        if (e != null) {
            entityManager.remove(e)
        } else {
            throw new ObjectNotFoundException("User with id='$id' not found")
        }
    }

}
