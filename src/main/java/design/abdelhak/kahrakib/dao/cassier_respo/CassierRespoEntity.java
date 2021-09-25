package design.abdelhak.kahrakib.dao.cassier_respo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import design.abdelhak.kahrakib.dao.chantier.ChantierEntity;
import design.abdelhak.kahrakib.dao.edc.EdsEntity;
import design.abdelhak.kahrakib.dao.utilisateur.UtilisateurEntity;
import design.abdelhak.kahrakib.dao.roles.RoleEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cassiers_respo")
public class CassierRespoEntity extends UtilisateurEntity {

    @JsonIgnoreProperties("cassierRespo")
    @OneToOne
    @JoinColumn(name = "chantier_fk")
    private ChantierEntity chantier;

    @JsonManagedReference
    @OneToMany(mappedBy = "cassierRespo",cascade = CascadeType.REMOVE)
    private List<EdsEntity> edss;

    public CassierRespoEntity() {
    }

    public CassierRespoEntity(String email, String motDePasse, String nom, String prenom,Date dateNaissance, RoleEntity role, Date dateCreation) {
        super(email, motDePasse, nom, prenom,dateNaissance, role, dateCreation);
    }

    public CassierRespoEntity(String email, String motDePasse, String nom, String prenom, Date dateNaissance, RoleEntity role, Date dateCreation, ChantierEntity chantier) {
        super(email, motDePasse, nom, prenom, dateNaissance, role, dateCreation);
        this.chantier = chantier;
    }

    public CassierRespoEntity(String email, String motDePasse, String nom, String prenom, Date dateNaissance, int age, String telephone, RoleEntity role, Date dateCreation, ChantierEntity chantier, List<EdsEntity> edss) {
        super(email, motDePasse, nom, prenom, dateNaissance, age, telephone, role, dateCreation);
        this.chantier = chantier;
        this.edss = edss;
    }

    public ChantierEntity getChantier() {
        return chantier;
    }

    public void setChantier(ChantierEntity chantier) {
        this.chantier = chantier;
    }

    public List<EdsEntity> getEdss() {
        return edss;
    }

    public void setEdss(List<EdsEntity> edss) {
        this.edss = edss;
    }
}
