package ma.projet.service;

import ma.projet.classes.Categorie;
import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommandeProduit;
import ma.projet.classes.Produit;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;


public class ProduitService extends AbstractFacade<Produit> {
    public ProduitService() {
        super(Produit.class);
    }

    // 1. Liste des produits par catégorie
    public List<Produit> findByCategorie(Categorie c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Produit p where p.categorie = :c");
        query.setParameter("c", c);
        List<Produit> produits = query.list();
        session.close();
        return produits;
    }

    // 2. Produits commandés entre deux dates
    public List<Produit> findOrderedBetweenDates(Date d1, Date d2) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select distinct l.produit from LigneCommandeProduit l where l.commande.date between :d1 and :d2");
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        List<Produit> produits = query.list();
        session.close();
        return produits;
    }

    // 3. Produits commandés dans une commande donnée
    public void showProductsInCommande(Commande c) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Commande fullCommande = session.get(Commande.class, c.getId());
        System.out.println("Commande : " + fullCommande.getId() + "     Date : " + fullCommande.getDate());
        System.out.println("Liste des produits :");
        System.out.println("Référence   Prix    Quantité");
        for (LigneCommandeProduit l : fullCommande.getLignes()) {
            System.out.println(l.getProduit().getReference() + "        " + l.getProduit().getPrix() + " DH  " + l.getQuantite());
        }
        session.close();
    }

    // 4. Produits dont le prix est supérieur à 100 DH (Named Query)
    public List<Produit> findPriceGreaterTo100() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("Produit.findByPriceGreaterThan100");
        List<Produit> produits = query.list();
        session.close();
        return produits;
    }
}
