package design.abdelhak.kahrakib.dto.requests;


import java.util.List;

public class EdsRequest {

    private Long cassierId;
    private List<Long> dpsId;

    public EdsRequest() {
    }

    public EdsRequest(Long cassierId, List<Long> dpsId) {
        this.cassierId = cassierId;
        this.dpsId = dpsId;
    }

    public Long getCassierId() {
        return cassierId;
    }

    public void setCassierId(Long cassierId) {
        this.cassierId = cassierId;
    }

    public List<Long> getDpsId() {
        return dpsId;
    }

    public void setDpsId(List<Long> dpsId) {
        this.dpsId = dpsId;
    }
}
