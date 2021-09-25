package design.abdelhak.kahrakib.dao.achat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import design.abdelhak.kahrakib.dao.dps.DpsEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "achats")
public class AchatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long achatId;

    private String designation;

    private int quantite;

    private BigDecimal prixUnitaire;

    private BigDecimal prixTotal;

    @JsonIgnoreProperties("achats")
    @ManyToOne
    @JoinColumn(name = "dps_fk")
    private DpsEntity dps;

    public AchatEntity() {
    }

    public AchatEntity(String designation, int quantite, BigDecimal prixUnitaire) {
        this.designation = designation;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.prixTotal = prixUnitaire.multiply(BigDecimal.valueOf(quantite));
    }

    public AchatEntity(String designation, int quantite, BigDecimal prixUnitaire, DpsEntity dps) {
        this.designation = designation;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.prixTotal = prixUnitaire.multiply(BigDecimal.valueOf(quantite));
        this.dps = dps;
    }

    public Long getAchatId() {
        return achatId;
    }

    public void setAchatId(Long achatId) {
        this.achatId = achatId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }

    public DpsEntity getDps() {
        return dps;
    }

    public void setDps(DpsEntity dps) {
        this.dps = dps;
    }
}
