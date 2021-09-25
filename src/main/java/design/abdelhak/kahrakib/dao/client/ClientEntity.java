package design.abdelhak.kahrakib.dao.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import design.abdelhak.kahrakib.dao.chantier.ChantierEntity;
import design.abdelhak.kahrakib.dao.dps.DpsEntity;
import design.abdelhak.kahrakib.dao.utilisateur.UtilisateurEntity;
import design.abdelhak.kahrakib.dao.roles.RoleEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "clients")
public class ClientEntity extends UtilisateurEntity {

    @JsonIgnoreProperties("clients")
    @ManyToOne
    @JoinColumn(name = "chantier_fk")
    private ChantierEntity chantier;


    @JsonManagedReference
    @OneToMany(mappedBy = "client",cascade = CascadeType.REMOVE)
    private List<DpsEntity> dpss = new ArrayList<>();

    public ClientEntity() {
    }

    public ClientEntity(String email, String motDePasse, String nom, String prenom,Date dateNaissance, RoleEntity role, Date dateCreation) {
        super(email, motDePasse, nom, prenom,dateNaissance, role, dateCreation);
    }

    public ClientEntity(String email, String motDePasse, String nom, String prenom, Date dateNaissance, RoleEntity role, Date dateCreation, ChantierEntity chantier) {
        super(email, motDePasse, nom, prenom, dateNaissance, role, dateCreation);
        this.chantier = chantier;
    }

    public ClientEntity(String email, String motDePasse, String nom, String prenom, Date dateNaissance, int age, String telephone, RoleEntity role, Date dateCreation, ChantierEntity chantier, List<DpsEntity> dpss) {
        super(email, motDePasse, nom, prenom, dateNaissance, age, telephone, role, dateCreation);
        this.chantier = chantier;
        this.dpss = dpss;
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
}
