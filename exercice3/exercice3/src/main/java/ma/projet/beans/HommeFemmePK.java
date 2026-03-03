package ma.projet.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;

@Embeddable
public class HommeFemmePK implements Serializable {
    private int idHomme;
    private int idFemme;
    private Date dateDebut;

    public HommeFemmePK() {}

    public HommeFemmePK(int idHomme, int idFemme, Date dateDebut) {
        this.idHomme = idHomme;
        this.idFemme = idFemme;
        this.dateDebut = dateDebut;
    }

    // Getters et setters
    public int getIdHomme() { return idHomme; }
    public void setIdHomme(int idHomme) { this.idHomme = idHomme; }
    public int getIdFemme() { return idFemme; }
    public void setIdFemme(int idFemme) { this.idFemme = idFemme; }
    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }

    // equals et hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HommeFemmePK that = (HommeFemmePK) o;
        return idHomme == that.idHomme && idFemme == that.idFemme && dateDebut.equals(that.dateDebut);
    }

    @Override
    public int hashCode() {
        int result = idHomme;
        result = 31 * result + idFemme;
        result = 31 * result + dateDebut.hashCode();
        return result;
    }
}
