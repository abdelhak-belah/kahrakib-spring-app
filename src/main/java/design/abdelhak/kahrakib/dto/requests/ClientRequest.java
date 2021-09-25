package design.abdelhak.kahrakib.dto.requests;

import java.util.Date;

public class ClientRequest {

    private String email;
    private String motDePasse;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String chantierImputation;

    public ClientRequest() {
    }

    public ClientRequest(String email, String motDePasse, String nom, String prenom, Date dateNaissance, String chantierImputation) {
        this.email = email;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.chantierImputation = chantierImputation;
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

    public String getChantierImputation() {
        return chantierImputation;
    }

    public void setChantierImputation(String chantierImputation) {
        this.chantierImputation = chantierImputation;
    }
}
