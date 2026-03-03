package ma.projet.test;

import ma.projet.classes.*;
import ma.projet.service.*;

import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        CategorieService cs = new CategorieService();
        ProduitService ps = new ProduitService();
        CommandeService coms = new CommandeService();
        LigneCommandeProduitService lcs = new LigneCommandeProduitService();


        Categorie c1 = new Categorie("INFO", "Informatique");
        Categorie c2 = new Categorie("TEL", "Téléphonie");
        cs.create(c1);
        cs.create(c2);


        Produit p1 = new Produit("ES12", 120, c1);
        Produit p2 = new Produit("ZR85", 100, c1);
        Produit p3 = new Produit("EE85", 200, c1);
        Produit p4 = new Produit("PH01", 1500, c2);
        ps.create(p1);
        ps.create(p2);
        ps.create(p3);
        ps.create(p4);


        Commande com1 = new Commande(new Date());
        coms.create(com1);

        lcs.create(new LigneCommandeProduit(7, p1, com1));
        lcs.create(new LigneCommandeProduit(14, p2, com1));
        lcs.create(new LigneCommandeProduit(5, p3, com1));

        // --- Test ---


        System.out.println("--- Produits par catégorie (Informatique) ---");
        List<Produit> infoProducts = ps.findByCategorie(c1);
        for (Produit p : infoProducts) {
            System.out.println(p.getReference() + " - " + p.getPrix() + " DH");
        }


        System.out.println("\n--- Produits commandés aujourd'hui ---");
        Date today = new Date();
        List<Produit> orderedToday = ps.findOrderedBetweenDates(today, today);
        for (Produit p : orderedToday) {
            System.out.println(p.getReference());
        }

        System.out.println("\n--- Détails de la commande ---");
        ps.showProductsInCommande(com1);


        System.out.println("\n--- Produits avec prix > 100 DH (Named Query) ---");
        List<Produit> expensiveProducts = ps.findPriceGreaterTo100();
        for (Produit p : expensiveProducts) {
            System.out.println(p.getReference() + " : " + p.getPrix() + " DH");
        }
    }
}
