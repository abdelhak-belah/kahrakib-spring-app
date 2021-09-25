package design.abdelhak.kahrakib.dao.elements;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "elements")
public class ElementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long elementId;

    @NotEmpty
    @Column(unique = true)
    private String nom;

    public ElementEntity() {
    }

    public ElementEntity(String nom) {
        this.nom = nom;
    }

    public Long getElementId() {
        return elementId;
    }

    public void setElementId(Long elementId) {
        this.elementId = elementId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
