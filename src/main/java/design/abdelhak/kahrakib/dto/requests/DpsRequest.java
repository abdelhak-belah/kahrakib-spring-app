package design.abdelhak.kahrakib.dto.requests;

import java.util.List;

public class DpsRequest {

    private String prestataire;
    private String adressePrestataire;
    private Long clientId;
    private List<AchatWithDpsRequest> achats;

    public DpsRequest() {
    }

    public DpsRequest(String prestataire, String adressePrestataire, Long clientId, List<AchatWithDpsRequest> achats) {
        this.prestataire = prestataire;
        this.adressePrestataire = adressePrestataire;
        this.clientId = clientId;
        this.achats = achats;
    }

    public String getPrestataire() {
        return prestataire;
    }

    public void setPrestataire(String prestataire) {
        this.prestataire = prestataire;
    }

    public String getAdressePrestataire() {
        return adressePrestataire;
    }

    public void setAdressePrestataire(String adressePrestataire) {
        this.adressePrestataire = adressePrestataire;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<AchatWithDpsRequest> getAchats() {
        return achats;
    }

    public void addAchats(AchatWithDpsRequest achatWithDpsRequest) {
        this.achats.add(achatWithDpsRequest);
    }
}
