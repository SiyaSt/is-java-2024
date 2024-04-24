package itmo.anastasiya.dao;

import java.util.List;

/**
 * dao methods
 *
 * @param <T> entity class
 * @author Anastasiya
 */
public interface AbstractDao<T> {
    /**
     * save entity
     * @param entity entity
     */
    void save(T entity);

    /**
     * delete entity by id
     * @param id entity`s id
     */
    void deleteById(long id);

    /**
     * delete entity
     * @param entity entity
     */
    void deleteByEntity(T entity);

    /**
     * update entity
     * @param entity entity
     */
    void update(T entity);

    /**
     * get entity by id
     * @param id entity`s id
     * @return entity
     */
    T getById(long id);

    /**
     * get all entities
     * @return list of entities
     */
    List<T> getAll();
}
