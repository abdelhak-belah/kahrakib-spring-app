package design.abdelhak.kahrakib.dao.direction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import design.abdelhak.kahrakib.dao.chantier.ChantierEntity;
import design.abdelhak.kahrakib.dao.comptable.ComptableEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "directions")
public class DirectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long directionId;

    @NotEmpty
    @Column(unique = true)
    private String nom;

    @NotEmpty
    private String imputation;

    @NotEmpty
    private String adresse;

    @JsonIgnoreProperties("direction")
    @OneToMany(mappedBy = "direction",cascade = CascadeType.REMOVE)
    private List<ChantierEntity> chantiers;


    @JsonIgnoreProperties("direction")
    @OneToOne(mappedBy = "direction")
    private ComptableEntity comptable;

    public DirectionEntity() {
    }

    public DirectionEntity(String nom, String imputation, String adresse) {
        this.nom = nom;
        this.imputation = imputation;
        this.adresse = adresse;
    }

    public Long getDirectionId() {
        return directionId;
    }

    public void setDirectionId(Long directionId) {
        this.directionId = directionId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImputation() {
        return imputation;
    }

    public void setImputation(String imputation) {
        this.imputation = imputation;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<ChantierEntity> getChantiers() {
        return chantiers;
    }

    public void setChantiers(List<ChantierEntity> chantiers) {
        this.chantiers = chantiers;
    }

    public ComptableEntity getComptable() {
        return comptable;
    }

    public void setComptable(ComptableEntity comptable) {
        this.comptable = comptable;
    }
}
