package design.abdelhak.kahrakib.dto.requests;

import java.math.BigDecimal;

public class AchatWithDpsRequest {
    private String designation;
    private int quantite;
    private BigDecimal prixUnitaire;

    public AchatWithDpsRequest() {
    }

    public AchatWithDpsRequest(String designation, int quantite, BigDecimal prixUnitaire) {
        this.designation = designation;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
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
}
