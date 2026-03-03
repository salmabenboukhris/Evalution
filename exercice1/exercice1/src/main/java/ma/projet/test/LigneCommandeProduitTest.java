package ma.projet.test;

import ma.projet.classes.*;
import ma.projet.service.*;
import java.util.Date;

public class LigneCommandeProduitTest {
    public static void main(String[] args) {
        CategorieService cs = new CategorieService();
        ProduitService ps = new ProduitService();
        CommandeService coms = new CommandeService();
        LigneCommandeProduitService lcs = new LigneCommandeProduitService();

        Categorie c = new Categorie("TEST", "Test Catégorie");
        cs.create(c);
        Produit p = new Produit("REFTEST", 150, c);
        ps.create(p);
        Commande com = new Commande(new Date());
        coms.create(com);
        LigneCommandeProduit lcp = new LigneCommandeProduit(3, p, com);
        boolean created = lcs.create(lcp);
        System.out.println("LigneCommandeProduit créée: " + created);
    }
}
