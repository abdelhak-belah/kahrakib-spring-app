package design.abdelhak.kahrakib.dao.chantier;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import design.abdelhak.kahrakib.dao.cassier.CassierEntity;
import design.abdelhak.kahrakib.dao.cassier_respo.CassierRespoEntity;
import design.abdelhak.kahrakib.dao.client.ClientEntity;
import design.abdelhak.kahrakib.dao.direction.DirectionEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "chantiers")
public class ChantierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chantierId;

    @NotEmpty
    private String nom;

    @NotEmpty
    @Column(unique = true)
    private String imputation;

    @NotEmpty
    private String adresse;

    @JsonIgnoreProperties("chantiers")
    @ManyToOne
    @JoinColumn(name = "direction_fk")
    private DirectionEntity direction;

    /*client*/
    @JsonIgnoreProperties("chantier")
    @OneToMany(mappedBy = "chantier",cascade = CascadeType.REMOVE)
    private List<ClientEntity> clients;

    /*cassier*/
    @JsonIgnoreProperties("chantier")
    @OneToOne(mappedBy = "chantier")
    private CassierEntity cassier;

    /*cassier responsable*/
    @JsonIgnoreProperties("chantier")
    @OneToOne(mappedBy = "chantier")
    private CassierRespoEntity cassierRespo;

    public ChantierEntity() {
    }

    public ChantierEntity(String nom, String adresse, DirectionEntity direction) {
        this.nom = nom;
        this.direction = direction;
        this.adresse = adresse;
        this.imputation = direction.getImputation()+"_"+nom;
    }

    public ChantierEntity(String nom, String adresse, DirectionEntity direction, List<ClientEntity> clientEntityList, CassierEntity cassier, CassierRespoEntity cassierRespo) {
        this.nom = nom;
        this.direction = direction;
        this.imputation = direction.getImputation()+"_"+nom;
        this.adresse = adresse;
        this.clients = clientEntityList;
        this.cassier = cassier;
        this.cassierRespo = cassierRespo;
    }

    public Long getChantierId() {
        return chantierId;
    }

    public void setChantierId(Long chantierId) {
        this.chantierId = chantierId;
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

    public DirectionEntity getDirection() {
        return direction;
    }

    public void setDirection(DirectionEntity direction) {
        this.direction = direction;
    }

    public List<ClientEntity> getClients() {
        return clients;
    }

    public void setClients(List<ClientEntity> clients) {
        this.clients = clients;
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
}
