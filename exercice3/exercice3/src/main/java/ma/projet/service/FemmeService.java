package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.beans.Mariage;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.Date;
import java.util.List;

public class FemmeService implements IDao<Femme> {
        // Méthode utilisant l’API Criteria pour afficher le nombre d’hommes mariés à quatre femmes entre deux dates
        public long countHommesMarieesAQuatreFemmesEntreDates(Date d1, Date d2) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "select m.homme.id from Mariage m where m.id.dateDebut between :d1 and :d2 group by m.homme.id having count(m.femme.id) = 4";
            Query query = session.createQuery(hql);
            query.setParameter("d1", d1);
            query.setParameter("d2", d2);
            List result = query.list();
            session.close();
            return result.size();
        }

        // Méthode pour afficher les mariages d’un homme donné, avec les détails (femme, dates, nombre d’enfants)
        public List<Mariage> getMariagesDetailsHomme(int hommeId) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "from Mariage m where m.homme.id = :hid";
            Query<Mariage> query = session.createQuery(hql, Mariage.class);
            query.setParameter("hid", hommeId);
            List<Mariage> result = query.list();
            session.close();
            return result;
        }
    @Override
    public boolean create(Femme o) {
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
    public boolean update(Femme o) {
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
    public boolean delete(Femme o) {
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
    public Femme findById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Femme f = session.get(Femme.class, id);
        session.close();
        return f;
    }

    @Override
    public List<Femme> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Femme> list = session.createQuery("from Femme", Femme.class).list();
        session.close();
        return list;
    }

    public int getNombreEnfantsEntreDates(int femmeId, Date d1, Date d2) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "SELECT SUM(nbrEnfant) FROM Mariage WHERE idFemme = ? AND dateDebut BETWEEN ? AND ?";
        Query query = session.createNativeQuery(sql);
        query.setParameter(1, femmeId);
        query.setParameter(2, new java.sql.Date(d1.getTime()));
        query.setParameter(3, new java.sql.Date(d2.getTime()));
        Object result = query.getSingleResult();
        session.close();
        return result != null ? ((Number) result).intValue() : 0;
    }

    public List<Femme> getFemmesMarieesDeuxFoisOuPlus() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "select f from Femme f where size(f.mariages) >= 2";
        Query<Femme> query = session.createQuery(hql, Femme.class);
        List<Femme> result = query.list();
        session.close();
        return result;
    }
}
