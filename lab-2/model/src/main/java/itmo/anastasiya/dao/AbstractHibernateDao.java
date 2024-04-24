package itmo.anastasiya.dao;

import itmo.anastasiya.dao.utils.HibernateSessionFactoryUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * abstract hibernate dao class
 *
 * @param <T> entity class
 * @author Anastasiya
 */
public abstract class AbstractHibernateDao<T> implements AbstractDao<T> {
    abstract protected Class<T> getEntityClass();

    //transaction, transaction manager
    public void save(T entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(entity);
        tx1.commit();
        session.close();
    }

    public void deleteById(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(getById(id));
        tx1.commit();
        session.close();
    }

    public void deleteByEntity(T entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.remove(entity);
        tx1.commit();
        session.close();
    }

    public void update(T entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(entity);
        tx1.commit();
        session.close();
    }

    public T getById(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        var object = session.get(getEntityClass(), id);
        session.close();
        return object;
    }

    public List<T> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(getEntityClass());
        criteria.from(getEntityClass());
        List<T> data = session.createQuery(criteria).getResultList();
        session.close();
        return data;
    }
}
