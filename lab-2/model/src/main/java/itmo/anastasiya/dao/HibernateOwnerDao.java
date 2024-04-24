package itmo.anastasiya.dao;

import itmo.anastasiya.entity.Owner;

/**
 * hibernate owner dao
 *
 * @author Anastasiya
 */
public class HibernateOwnerDao extends AbstractHibernateDao<Owner> implements OwnerDao {

    @Override
    protected Class<Owner> getEntityClass() {
        return Owner.class;
    }
}
