package ma.project.classes;


import javax.persistence.*;
import java.util.List;

@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String reference;
    private Float prix;
    @ManyToOne
    @JoinColumn(name="categorie_id")
    private Categorie categorie;
    @OneToMany(mappedBy = "produit")
    private List<LigneCommandeProduit> ligneCommandeProduits;


    public Produit(Categorie categorie, int id, Float prix, String reference) {
        this.categorie = categorie;
        this.id = id;
        this.prix = prix;
        this.reference = reference;
    }

    public Produit() {
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
