package design.abdelhak.kahrakib.dto.requests;

public class EtatRequest {

    private String etat;

    public EtatRequest() {
    }

    public EtatRequest(String etat) {
        this.etat = etat;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
