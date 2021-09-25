package design.abdelhak.kahrakib.dao.utilisateur;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import design.abdelhak.kahrakib.dao.roles.RoleEntity;
import design.abdelhak.kahrakib.utils.AgeCalculator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Table(name = "utilisateurs")
@Inheritance(strategy = InheritanceType.JOINED)
public class UtilisateurEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long utilisateurId;

    @Column(unique = true)
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 6, max = 100, message = "Le mot de passe doit être compris entre {min} et {max} caractères")
    private String motDePasse;

    @NotEmpty
    private String nom;

    @NotEmpty
    private String prenom;

    @Past
    @NotEmpty
    private Date dateNaissance;

    @Min(value = 18,message = "l'âge doit être supérieur à {value}")
    @NotEmpty
    private int age;

    private String telephone;


    @JsonIgnoreProperties("utilisateurs")
    @ManyToOne
    @JoinColumn(name = "role_fk")
    private RoleEntity role;

    @NotEmpty
    @Column(updatable = false)
    private Date dateCreation;

    public UtilisateurEntity() {
    }

    public UtilisateurEntity(String email, String motDePasse, String nom, String prenom,Date dateNaissance, RoleEntity role, Date dateCreation) {
        this.email = email;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.age = AgeCalculator.avecDateNaissance(dateNaissance);
        this.role = role;
        this.dateCreation = dateCreation;
    }

    public UtilisateurEntity(String email, String motDePasse, String nom, String prenom, Date dateNaissance, int age, String telephone, RoleEntity role, Date dateCreation) {
        this.email = email;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.age = age;
        this.telephone = telephone;
        this.role = role;
        this.dateCreation = dateCreation;
    }

    public Long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}
