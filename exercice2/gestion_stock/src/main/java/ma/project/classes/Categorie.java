package ma.project.classes;
import java.util.List;
import javax.persistence.*;

@Entity
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String code;
    private String libelle;

    public Categorie(String code, long id, String libelle) {
        this.code = code;
        this.id = id;
        this.libelle = libelle;
    }

    @OneToMany(mappedBy = "categorie", fetch = FetchType.EAGER)
    private List<Produit> produits;
    public Categorie() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
