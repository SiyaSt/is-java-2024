package itmo.anastasiya.dao;

import itmo.anastasiya.entity.Cat;

/**
 * hibernate cat dao
 *
 * @author Anastasiya
 */
public class HibernateCatDao extends AbstractHibernateDao<Cat> implements CatDao {
    @Override
    protected Class<Cat> getEntityClass() {
        return Cat.class;
    }
}
