package design.abdelhak.kahrakib.dto.requests;

public class EtatEdsRequest {

    private String etatEds;

    public EtatEdsRequest() {
    }

    public EtatEdsRequest(String etatEds) {
        this.etatEds = etatEds;
    }

    public String getEtatEds() {
        return etatEds;
    }

    public void setEtatEds(String etatEds) {
        this.etatEds = etatEds;
    }
}
