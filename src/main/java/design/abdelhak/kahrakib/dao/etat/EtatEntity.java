package design.abdelhak.kahrakib.dao.etat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import design.abdelhak.kahrakib.dao.edc.EdsEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "etats")
public class EtatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long etatId;

    private String etat;

    @JsonIgnoreProperties("etat")
    @OneToMany(mappedBy = "etat",cascade = CascadeType.REMOVE)
    private List<EdsEntity> edss;

    public EtatEntity() {
    }


    public EtatEntity(String etat) {
        this.etat = etat;
    }

    public EtatEntity(String etat, List<EdsEntity> edss) {
        this.etat = etat;
        this.edss = edss;
    }

    public Long getEtatId() {
        return etatId;
    }

    public void setEtatId(Long etatId) {
        this.etatId = etatId;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public List<EdsEntity> getEdss() {
        return edss;
    }

    public void setEdss(List<EdsEntity> edss) {
        this.edss = edss;
    }
}
