package ma.projet.test;

import ma.projet.classes.Categorie;
import ma.projet.service.CategorieService;

public class CategorieTest {
    public static void main(String[] args) {
        CategorieService cs = new CategorieService();
        Categorie c = new Categorie("TEST", "Test Catégorie");
        boolean created = cs.create(c);
        System.out.println("Catégorie créée: " + created);
    }
}
