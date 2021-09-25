package design.abdelhak.kahrakib.dao.edc;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import design.abdelhak.kahrakib.dao.cassier.CassierEntity;
import design.abdelhak.kahrakib.dao.cassier_respo.CassierRespoEntity;
import design.abdelhak.kahrakib.dao.comptable.ComptableEntity;
import design.abdelhak.kahrakib.dao.dps.DpsEntity;
import design.abdelhak.kahrakib.dao.etat.EtatEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "edss")
public class EdsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long edsId;

    private String imputation;

    private BigDecimal montantGlobal;

    @Column(updatable = false)
    private Date dateCreation;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "comptable_fk")
    private ComptableEntity comptable;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cassier_fk")
    private CassierEntity cassier;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cassier_respo_fk")
    private CassierRespoEntity cassierRespo;


    @JsonIgnoreProperties("eds")
    @OneToMany(mappedBy = "eds",cascade = CascadeType.REMOVE)
    private List<DpsEntity> dpss = new ArrayList<>();;

    @JsonIgnoreProperties("edss")
    @ManyToOne
    @JoinColumn(name = "etat_fk")
    private EtatEntity etat;

    public EdsEntity() {
    }

    public EdsEntity(String imputation, BigDecimal montantGlobal, ComptableEntity comptable, CassierEntity cassier, CassierRespoEntity cassierRespo, List<DpsEntity> dpss, EtatEntity etat) {
        this.imputation = imputation;
        this.montantGlobal = montantGlobal;
        this.dateCreation = new Date(System.currentTimeMillis());
        this.comptable = comptable;
        this.cassier = cassier;
        this.cassierRespo = cassierRespo;
        this.dpss = dpss;
        this.etat = etat;
    }

    public Long getEdsId() {
        return edsId;
    }

    public void setEdsId(Long edsId) {
        this.edsId = edsId;
    }

    public String getImputation() {
        return imputation;
    }

    public void setImputation(String imputation) {
        this.imputation = imputation;
    }

    public BigDecimal getMontantGlobal() {
        return montantGlobal;
    }

    public void setMontantGlobal(BigDecimal montantGlobal) {
        this.montantGlobal = montantGlobal;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public ComptableEntity getComptable() {
        return comptable;
    }

    public void setComptable(ComptableEntity comptable) {
        this.comptable = comptable;
    }

    public CassierEntity getCassier() {
        return cassier;
    }

    public void setCassier(CassierEntity cassier) {
        this.cassier = cassier;
    }

    public CassierRespoEntity getCassierRespo() {
        return cassierRespo;
    }

    public void setCassierRespo(CassierRespoEntity cassierRespo) {
        this.cassierRespo = cassierRespo;
    }

    public List<DpsEntity> getDpss() {
        return dpss;
    }

    public void setDpss(List<DpsEntity> dpss) {
        this.dpss = dpss;
    }

    public EtatEntity getEtat() {
        return etat;
    }

    public void setEtat(EtatEntity etat) {
        this.etat = etat;
    }
}
