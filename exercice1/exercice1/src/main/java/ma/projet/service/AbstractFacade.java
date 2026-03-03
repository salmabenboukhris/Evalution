 package ma.projet.service;

import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public abstract class AbstractFacade<T> {
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public boolean create(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public T findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        T obj = session.get(entityClass, id);
        session.close();
        return obj;
    }

    public List<T> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<T> list = session.createQuery("from " + entityClass.getSimpleName()).list();
        session.close();
        return list;
    }
}
