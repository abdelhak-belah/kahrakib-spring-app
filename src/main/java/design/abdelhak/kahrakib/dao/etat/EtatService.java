package design.abdelhak.kahrakib.dao.etat;

import design.abdelhak.kahrakib.dto.requests.EtatRequest;
import design.abdelhak.kahrakib.dto.responses.DeleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtatService {

    @Autowired
    private EtatRepository etatRepository;

    public List<EtatEntity> getAllEtats() {
        return etatRepository.findAll();
    }

    public EtatEntity getEtatById(Long etatId) {
        return etatRepository.findById(etatId).get();
    }

    public EtatEntity saveEtat(EtatRequest etatRequest) {
        EtatEntity etatEntity = new EtatEntity(
                etatRequest.getEtat()
        );

        return etatRepository.save(etatEntity);
    }

    public EtatEntity updateEtat(Long etatId, EtatEntity etatEntity) {
        EtatEntity currentEtat = etatRepository.findById(etatId).get();

        currentEtat.setEtat(etatEntity.getEtat());
        return etatRepository.save(currentEtat);
    }

    public DeleteResponse deleteEtat(Long etatId) {
        Long id = etatId;
        etatRepository.deleteById(etatId);
        return new DeleteResponse(id, "etat supprimé avec succès");
    }
}
