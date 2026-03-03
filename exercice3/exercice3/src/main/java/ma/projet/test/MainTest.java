package ma.projet.test;

import ma.projet.beans.*;
import ma.projet.service.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainTest {
    public static void main(String[] args) throws Exception {
        HommeService hommeService = new HommeService();
        FemmeService femmeService = new FemmeService();
        MariageService mariageService = new MariageService();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Création de 5 hommes avec noms personnalisés
        String[] nomsHommes = {"SAFI", "BENALI", "AMRANI", "EL MANSOURI", "KHALIL"};
        String[] prenomsHommes = {"SAID", "OMAR", "YASSINE", "NABIL", "SAMIR"};
        for (int i = 0; i < 5; i++) {
            Homme h = new Homme();
            h.setNom(nomsHommes[i]);
            h.setPrenom(prenomsHommes[i]);
            h.setTelephone("060000000" + (i+1));
            h.setAdresse("AdresseH" + (i+1));
            h.setDateNaissance(sdf.parse("01/01/198" + (i+1)));
            hommeService.create(h);
        }

        // Création de 10 femmes avec noms personnalisés
        String[] nomsFemmes = {"SALIMA", "AMAL", "WAFA", "KARIMA", "NADIA", "FATIMA", "HANA", "IMANE", "RANIA", "LINA"};
        String[] prenomsFemmes = {"RAMI", "ALI", "ALAOUI", "ALAMI", "BOUCHRA", "ZAHRA", "SOUAD", "SARA", "JIHANE", "MERYEM"};
        for (int i = 0; i < 10; i++) {
            Femme f = new Femme();
            f.setNom(nomsFemmes[i]);
            f.setPrenom(prenomsFemmes[i]);
            f.setTelephone("070000000" + (i+1));
            f.setAdresse("AdresseF" + (i+1));
            f.setDateNaissance(sdf.parse("01/01/197" + (i+1)));
            femmeService.create(f);
        }

        // Création de quelques mariages avec date de fin non null
        List<Homme> hommes = hommeService.findAll();
        List<Femme> femmes = femmeService.findAll();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                int femmeIndex = i * 2 + j;
                if (femmeIndex < femmes.size()) {
                    Mariage m = new Mariage();
                    Homme hommeObj = hommes.get(i);
                    Femme femmeObj = femmes.get(femmeIndex);
                    Date debut = sdf.parse("03/09/199" + (i + j));
                    // Clé composite
                    HommeFemmePK pk = new HommeFemmePK(hommeObj.getId(), femmeObj.getId(), debut);
                    m.setId(pk);
                    m.setHomme(hommeObj);
                    m.setFemme(femmeObj);
                    // Ajoute une date de fin 5 ans après la date de début
                    Date fin = new Date(debut.getTime());
                    fin.setYear(debut.getYear() + 5); // deprecated mais simple ici
                    m.setDateFin(fin);
                    m.setNbrEnfant(i + j);
                    mariageService.create(m);
                }
            }
        }

        // Afficher la liste des femmes
        System.out.println("Liste des femmes :");
        femmes = femmeService.findAll();
        femmes.forEach(f -> System.out.println(f.getNom() + " " + f.getPrenom()));

        // Afficher la femme la plus âgée
        Femme plusAgee = femmes.stream().min((f1, f2) -> f1.getDateNaissance().compareTo(f2.getDateNaissance())).get();
        System.out.println("Femme la plus âgée : " + plusAgee.getNom() + " " + plusAgee.getPrenom());

        // Afficher les épouses d’un homme donné (exemple pour le premier homme)
        Homme homme = hommes.get(0);
        List<Femme> epouses = hommeService.getEpousesEntreDates(homme.getId(), sdf.parse("01/01/1990"), sdf.parse("31/12/2000"));
        System.out.println("Epouses de " + homme.getNom() + " :");
        epouses.forEach(f -> System.out.println(f.getNom() + " " + f.getPrenom()));

        // Afficher le nombre d’enfants d’une femme entre deux dates (exemple pour la première femme)
        Femme femme = femmes.get(0);
        int nbEnfants = femmeService.getNombreEnfantsEntreDates(femme.getId(), sdf.parse("01/01/1990"), sdf.parse("31/12/2000"));
        System.out.println("Nombre d'enfants de " + femme.getNom() + " entre 1990 et 2000 : " + nbEnfants);

        // Afficher les femmes mariées deux fois ou plus
        List<Femme> femmes2x = femmeService.getFemmesMarieesDeuxFoisOuPlus();
        System.out.println("Femmes mariées au moins deux fois :");
        femmes2x.forEach(f -> System.out.println(f.getNom() + " " + f.getPrenom()));

        // Afficher le nombre d’hommes mariés à quatre femmes entre deux dates
        long nbHommes4F = femmeService.countHommesMarieesAQuatreFemmesEntreDates(sdf.parse("01/01/1990"), sdf.parse("31/12/2000"));
        System.out.println("Nombre d'hommes mariés à 4 femmes entre 1990 et 2000 : " + nbHommes4F);

        // Afficher les mariages d’un homme avec tous les détails
        List<Mariage> mariages = hommeService.getMariagesDetails(homme.getId());
        System.out.println("Mariages de " + homme.getNom() + " :");
        for (Mariage m : mariages) {
            System.out.println("Femme : " + m.getFemme().getNom() + " " + m.getFemme().getPrenom() +
                    " | Date Début : " + sdf.format(m.getDateDebut()) +
                    (m.getDateFin() != null ? " | Date Fin : " + sdf.format(m.getDateFin()) : "") +
                    " | Nbr Enfants : " + m.getNbrEnfant());
        }
    }
}
