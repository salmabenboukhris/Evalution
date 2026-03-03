package ma.projet.test;

import ma.projet.classes.*;
import ma.projet.service.*;
import java.util.Date;
import java.util.List;

public class ProduitTest {
    public static void main(String[] args) {
        CategorieService cs = new CategorieService();
        ProduitService ps = new ProduitService();
        CommandeService coms = new CommandeService();
        LigneCommandeProduitService lcs = new LigneCommandeProduitService();

        // Création de catégories et produits
        Categorie c1 = new Categorie("INFO", "Informatique");
        cs.create(c1);
        Produit p1 = new Produit("ES12", 120, c1);
        Produit p2 = new Produit("ZR85", 100, c1);
        Produit p3 = new Produit("EE85", 200, c1);
        ps.create(p1);
        ps.create(p2);
        ps.create(p3);

        // Création d'une commande et lignes
        Commande com1 = new Commande(new Date());
        coms.create(com1);
        lcs.create(new LigneCommandeProduit(7, p1, com1));
        lcs.create(new LigneCommandeProduit(14, p2, com1));
        lcs.create(new LigneCommandeProduit(5, p3, com1));

        // Test 1 : Produits par catégorie
        System.out.println("--- Produits par catégorie (Informatique) ---");
        List<Produit> infoProducts = ps.findByCategorie(c1);
        for (Produit p : infoProducts) {
            System.out.println(p.getReference() + " - " + p.getPrix() + " DH");
        }

        // Test 2 : Produits commandés aujourd'hui
        Date today = new Date();
        System.out.println("\n--- Produits commandés aujourd'hui ---");
        List<Produit> orderedToday = ps.findOrderedBetweenDates(today, today);
        for (Produit p : orderedToday) {
            System.out.println(p.getReference());
        }

        // Test 3 : Détail d'une commande
        System.out.println("\n--- Détails de la commande ---");
        ps.showProductsInCommande(com1);

        // Test 4 : Produits prix > 100
        System.out.println("\n--- Produits avec prix > 100 DH (Named Query) ---");
        List<Produit> expensiveProducts = ps.findPriceGreaterTo100();
        for (Produit p : expensiveProducts) {
            System.out.println(p.getReference() + " : " + p.getPrix() + " DH");
        }
    }
}
