package design.abdelhak.kahrakib.dao.dps;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import design.abdelhak.kahrakib.dao.achat.AchatEntity;
import design.abdelhak.kahrakib.dao.cassier.CassierEntity;
import design.abdelhak.kahrakib.dao.client.ClientEntity;
import design.abdelhak.kahrakib.dao.edc.EdsEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "dpss")
public class DpsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dpsId;

    private String imputation;

    private Boolean etatClient = false;

    private Boolean etatCassier = false;

    private String prestataire;

    private String adressePrestataire;

    private BigDecimal totalAchatMontant;

    @NotEmpty
    @Column(updatable = false)
    private Date dateDeCreation;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "client_fk")
    private ClientEntity client;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cassier_fk")
    private CassierEntity cassier;

    @JsonIgnoreProperties("dps")
    @OneToMany(mappedBy = "dps",cascade = CascadeType.REMOVE)
    private List<AchatEntity> achats;

    @JsonIgnoreProperties("dpss")
    @ManyToOne
    @JoinColumn(name = "eds_fk")
    private EdsEntity eds;

    public DpsEntity() {
    }

    public DpsEntity(String imputation, String prestataire, String adressePrestataire, ClientEntity client, CassierEntity cassier) {
        this.imputation = imputation;
        this.prestataire = prestataire;
        this.adressePrestataire = adressePrestataire;
        this.dateDeCreation = new Date(System.currentTimeMillis());
        this.client = client;
        this.cassier = cassier;
    }

    public DpsEntity(String imputation, String prestataire, String adressePrestataire, ClientEntity client, CassierEntity cassier, List<AchatEntity> achats) {
        this.imputation = imputation;
        this.prestataire = prestataire;
        this.adressePrestataire = adressePrestataire;
        this.dateDeCreation = new Date(System.currentTimeMillis());
        this.client = client;
        this.cassier = cassier;
        this.achats = achats;
    }

    public Long getDpsId() {
        return dpsId;
    }

    public void setDpsId(Long dpsId) {
        this.dpsId = dpsId;
    }

    public String getImputation() {
        return imputation;
    }

    public void setImputation(String imputation) {
        this.imputation = imputation;
    }

    public Boolean getEtatClient() {
        return etatClient;
    }

    public void setEtatClient(Boolean etatClient) {
        this.etatClient = etatClient;
    }

    public Boolean getEtatCassier() {
        return etatCassier;
    }

    public void setEtatCassier(Boolean etatCassier) {
        this.etatCassier = etatCassier;
    }

    public String getPrestataire() {
        return prestataire;
    }

    public void setPrestataire(String prestataire) {
        this.prestataire = prestataire;
    }

    public String getAdressePrestataire() {
        return adressePrestataire;
    }

    public void setAdressePrestataire(String adressePrestataire) {
        this.adressePrestataire = adressePrestataire;
    }

    public Date getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(Date dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public CassierEntity getCassier() {
        return cassier;
    }

    public void setCassier(CassierEntity cassier) {
        this.cassier = cassier;
    }

    public List<AchatEntity> getAchats() {
        return achats;
    }

    public void setAchats(List<AchatEntity> achats) {
        this.achats = achats;
    }

    public EdsEntity getEds() {
        return eds;
    }

    public void setEds(EdsEntity eds) {
        this.eds = eds;
    }

    public BigDecimal getTotalAchatMontant() {
        return totalAchatMontant;
    }

    public void setTotalAchatMontant(BigDecimal totalAchatMontant) {
        this.totalAchatMontant = totalAchatMontant;
    }
}
