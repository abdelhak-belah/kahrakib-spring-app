package design.abdelhak.kahrakib.dto.requests;

public class EtatCassierRequest {

    private Boolean etatCassier = false;

    public EtatCassierRequest() {
    }

    public EtatCassierRequest(Boolean etatCassier) {
        this.etatCassier = etatCassier;
    }

    public Boolean getEtatCassier() {
        return etatCassier;
    }

    public void setEtatCassier(Boolean etatCassier) {
        this.etatCassier = etatCassier;
    }
}
