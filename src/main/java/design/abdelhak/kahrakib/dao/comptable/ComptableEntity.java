package design.abdelhak.kahrakib.dao.comptable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import design.abdelhak.kahrakib.dao.direction.DirectionEntity;
import design.abdelhak.kahrakib.dao.edc.EdsEntity;
import design.abdelhak.kahrakib.dao.utilisateur.UtilisateurEntity;
import design.abdelhak.kahrakib.dao.roles.RoleEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comptables")
public class ComptableEntity extends UtilisateurEntity {

    @JsonIgnoreProperties("comptable")
    @OneToOne
    @JoinColumn(name = "direction_fk")
    private DirectionEntity direction;

    @JsonManagedReference
    @OneToMany(mappedBy = "comptable")
    private List<EdsEntity> edss;

    public ComptableEntity() {
    }

    public ComptableEntity(String email, String motDePasse, String nom, String prenom,Date dateNaissance, RoleEntity role, Date dateCreation,DirectionEntity directionEntity) {
        super(email, motDePasse, nom, prenom,dateNaissance, role, dateCreation);
        this.direction = directionEntity;
    }

    public ComptableEntity(String email, String motDePasse, String nom, String prenom, Date dateNaissance, int age, String telephone, RoleEntity role, Date dateCreation, DirectionEntity direction, List<EdsEntity> edss) {
        super(email, motDePasse, nom, prenom, dateNaissance, age, telephone, role, dateCreation);
        this.direction = direction;
        this.edss = edss;
    }

    public ComptableEntity(String email, String motDePasse, String nom, String prenom, Date dateNaissance, RoleEntity role, Date dateCreation) {
        super(email, motDePasse, nom, prenom, dateNaissance, role, dateCreation);
    }

    public DirectionEntity getDirection() {
        return direction;
    }

    public void setDirection(DirectionEntity direction) {
        this.direction = direction;
    }

    public List<EdsEntity> getEdss() {
        return edss;
    }

    public void setEdss(List<EdsEntity> edss) {
        this.edss = edss;
    }
}
