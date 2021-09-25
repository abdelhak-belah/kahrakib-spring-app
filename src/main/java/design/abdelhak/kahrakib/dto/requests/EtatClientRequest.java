package design.abdelhak.kahrakib.dto.requests;

public class EtatClientRequest {

    private Boolean etatClient = false;

    public EtatClientRequest() {
    }

    public EtatClientRequest(Boolean etatClient) {
        this.etatClient = etatClient;
    }

    public Boolean getEtatClient() {
        return etatClient;
    }

    public void setEtatClient(Boolean etatClient) {
        this.etatClient = etatClient;
    }
}
