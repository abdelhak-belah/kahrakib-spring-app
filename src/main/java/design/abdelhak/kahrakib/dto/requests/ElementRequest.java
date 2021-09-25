package design.abdelhak.kahrakib.dto.requests;

public class ElementRequest {

    private String nom;

    public ElementRequest() {
    }

    public ElementRequest(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
