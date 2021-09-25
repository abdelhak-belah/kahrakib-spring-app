package design.abdelhak.kahrakib.dto.requests;


public class DirectionRequest {

    private String nom;
    private String imputation;
    private String adresse;

    public DirectionRequest() {
    }

    public DirectionRequest(String nom, String imputation, String adresse) {
        this.nom = nom;
        this.imputation = imputation;
        this.adresse = adresse;
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
}
