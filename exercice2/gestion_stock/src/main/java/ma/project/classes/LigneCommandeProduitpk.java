package ma.project.classes;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class LigneCommandeProduitpk implements Serializable {
    private int produitId;
    private int commandeId;

    public LigneCommandeProduitpk() {}

    public LigneCommandeProduitpk(int produitId, int commandeId) {
        this.produitId = produitId;
        this.commandeId = commandeId;
    }

    public int getProduitId() {
        return produitId;
    }

    public void setProduitId(int produitId) {
        this.produitId = produitId;
    }

    public int getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(int commandeId) {
        this.commandeId = commandeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LigneCommandeProduitpk that = (LigneCommandeProduitpk) o;
        return produitId == that.produitId && commandeId == that.commandeId;
    }

    @Override
    public int hashCode() {
        return 31 * produitId + commandeId;
    }
}