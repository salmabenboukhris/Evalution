package ma.projet.service;

import ma.projet.beans.Homme;
import ma.projet.beans.Femme;
import ma.projet.beans.Mariage;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.Date;
import java.util.List;

public class HommeService implements IDao<Homme> {
    @Override
    public boolean create(Homme o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Homme o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(o);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(Homme o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(o);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public Homme findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Homme h = session.get(Homme.class, id);
        session.close();
        return h;
    }

    @Override
    public List<Homme> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Homme> list = session.createQuery("from Homme", Homme.class).list();
        session.close();
        return list;
    }

    public List<Femme> getEpousesEntreDates(int hommeId, Date d1, Date d2) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "select m.femme from Mariage m where m.homme.id = :hid and m.id.dateDebut between :d1 and :d2";
        Query<Femme> query = session.createQuery(hql, Femme.class);
        query.setParameter("hid", hommeId);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        List<Femme> result = query.list();
        session.close();
        return result;
    }

    public List<Mariage> getMariagesDetails(int hommeId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from Mariage m where m.homme.id = :hid";
        Query<Mariage> query = session.createQuery(hql, Mariage.class);
        query.setParameter("hid", hommeId);
        List<Mariage> result = query.list();
        session.close();
        return result;
    }
}
