package ma.project.classes;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date date;
        @OneToMany(mappedBy = "commande")
        private List<LigneCommandeProduit> ligneCommandeProduits;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Commande(Date date, int id, List<Produit> produits) {
        this.date = date;
        this.id = id;

    }

    public Commande() {
    }
}
