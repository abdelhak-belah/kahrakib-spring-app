package design.abdelhak.kahrakib.dao.achat;

import design.abdelhak.kahrakib.dto.requests.AchatRequest;
import design.abdelhak.kahrakib.dto.responses.DeleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AchatService {

    @Autowired
    private AchatRepository achatRepository;

    public List<AchatEntity> getAllAchats() {
        return achatRepository.findAll();
    }

    public AchatEntity getAchatById(Long achatId) {
        return achatRepository.findById(achatId).get();
    }

    public AchatEntity saveAchat(AchatRequest achatRequest) {
        AchatEntity achatEntity = new AchatEntity(
                achatRequest.getDesignation(),
                achatRequest.getQuantite(),
                achatRequest.getPrixUnitaire()
        );
        return achatRepository.save(achatEntity);
    }

    public AchatEntity updateAchat(Long achatId, AchatEntity achatEntity) {
        AchatEntity currentAchat = achatRepository.findById(achatId).get();

        currentAchat.setDesignation(achatEntity.getDesignation());
        currentAchat.setQuantite(achatEntity.getQuantite());
        currentAchat.setPrixUnitaire(achatEntity.getPrixUnitaire());
        currentAchat.setPrixTotal(currentAchat.getPrixUnitaire().multiply(BigDecimal.valueOf(currentAchat.getQuantite())));
        return achatRepository.save(currentAchat);
    }

    public DeleteResponse deleteAchat(Long achatId) {
        Long id = achatId;
        achatRepository.deleteById(achatId);
        return new DeleteResponse(id, "achat supprimé avec succès");
    }
}
