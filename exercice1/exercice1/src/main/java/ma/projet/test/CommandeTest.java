package ma.projet.test;

import ma.projet.classes.Commande;
import ma.projet.service.CommandeService;
import java.util.Date;

public class CommandeTest {
    public static void main(String[] args) {
        CommandeService cs = new CommandeService();
        Commande c = new Commande(new Date());
        boolean created = cs.create(c);
        System.out.println("Commande créée: " + created);
    }
}
