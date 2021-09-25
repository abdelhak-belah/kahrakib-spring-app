package design.abdelhak.kahrakib.dao.cassier;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import design.abdelhak.kahrakib.dao.chantier.ChantierEntity;
import design.abdelhak.kahrakib.dao.dps.DpsEntity;
import design.abdelhak.kahrakib.dao.edc.EdsEntity;
import design.abdelhak.kahrakib.dao.utilisateur.UtilisateurEntity;
import design.abdelhak.kahrakib.dao.roles.RoleEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cassiers")
public class CassierEntity extends UtilisateurEntity {

    private BigDecimal budget;

    @JsonIgnoreProperties("cassier")
    @OneToOne
    @JoinColumn(name = "chantier_fk")
    private ChantierEntity chantier;

    @JsonManagedReference
    @OneToMany(mappedBy = "cassier",cascade = CascadeType.REMOVE)
    private List<DpsEntity> dpss = new ArrayList<>();;

    @JsonManagedReference
    @OneToMany(mappedBy = "cassier",cascade = CascadeType.REMOVE)
    private List<EdsEntity> edss = new ArrayList<>();;


    public CassierEntity() {
    }

    public CassierEntity(BigDecimal budget) {
        this.budget = budget;
    }

    public CassierEntity(String email, String motDePasse, String nom, String prenom,Date dateNaissance,RoleEntity role, Date dateCreation) {
        super(email, motDePasse, nom, prenom,dateNaissance,role, dateCreation);
        this.budget = new BigDecimal(20000);
    }

    public CassierEntity(String email, String motDePasse, String nom, String prenom, Date dateNaissance,RoleEntity role, Date dateCreation, ChantierEntity chantier) {
        super(email, motDePasse, nom, prenom, dateNaissance, role, dateCreation);
        this.budget = new BigDecimal(20000);
        this.chantier = chantier;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public ChantierEntity getChantier() {
        return chantier;
    }

    public void setChantier(ChantierEntity chantier) {
        this.chantier = chantier;
    }

    public List<DpsEntity> getDpss() {
        return dpss;
    }

    public void setDpss(List<DpsEntity> dpss) {
        this.dpss = dpss;
    }

    public List<EdsEntity> getEdss() {
        return edss;
    }

    public void setEdss(List<EdsEntity> edss) {
        this.edss = edss;
    }
}
