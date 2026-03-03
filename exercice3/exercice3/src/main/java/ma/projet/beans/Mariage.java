package ma.projet.beans;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Mariage {
    @EmbeddedId
    private HommeFemmePK id;

    @ManyToOne
    @MapsId("idHomme")
    @JoinColumn(name = "idHomme")
    private Homme homme;

    @ManyToOne
    @MapsId("idFemme")
    @JoinColumn(name = "idFemme")
    private Femme femme;

    @Temporal(TemporalType.DATE)
    private Date dateFin;
    private int nbrEnfant;

    public HommeFemmePK getId() { return id; }
    public void setId(HommeFemmePK id) { this.id = id; }
    public Homme getHomme() { return homme; }
    public void setHomme(Homme homme) { this.homme = homme; }
    public Femme getFemme() { return femme; }
    public void setFemme(Femme femme) { this.femme = femme; }
    public Date getDateDebut() { return id != null ? id.getDateDebut() : null; }
    public void setDateDebut(Date dateDebut) { if (id != null) id.setDateDebut(dateDebut); }
    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }
    public int getNbrEnfant() { return nbrEnfant; }
    public void setNbrEnfant(int nbrEnfant) { this.nbrEnfant = nbrEnfant; }
}
