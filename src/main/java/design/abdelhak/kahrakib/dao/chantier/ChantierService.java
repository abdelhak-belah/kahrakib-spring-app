package design.abdelhak.kahrakib.dao.chantier;

import design.abdelhak.kahrakib.dao.direction.DirectionEntity;
import design.abdelhak.kahrakib.dao.direction.DirectionRepository;
import design.abdelhak.kahrakib.dto.requests.ChantierRequest;
import design.abdelhak.kahrakib.dto.responses.DeleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChantierService {

    @Autowired
    private ChantierRepository chantierRepository;

    @Autowired
    private DirectionRepository directionRepository;

    public List<ChantierEntity> getAllChantiers() {
        return chantierRepository.findAll();
    }

    public ChantierEntity getChantierById(Long chantierId) {
        return chantierRepository.findById(chantierId).get();
    }

    public ChantierEntity saveChantier(ChantierRequest chantierRequest) {
        DirectionEntity directionEntity = directionRepository.findDirectionEntityByImputation(chantierRequest.getDirectionImputation());
        ChantierEntity chantierEntity = new ChantierEntity(
                chantierRequest.getNom(),
                chantierRequest.getAdresse(),
                directionEntity
        );
        return chantierRepository.save(chantierEntity);
    }

    public ChantierEntity updateChantier(Long chantierId, ChantierRequest chantierRequest) {
        ChantierEntity currentChantier = chantierRepository.findById(chantierId).get();
        DirectionEntity newDirection = directionRepository.findDirectionEntityByImputation(chantierRequest.getDirectionImputation());

        currentChantier.setDirection(newDirection);
        currentChantier.setNom(chantierRequest.getNom());
        currentChantier.setImputation(newDirection.getImputation() + "_" + currentChantier.getNom());
        currentChantier.setAdresse(chantierRequest.getAdresse());
        return chantierRepository.save(currentChantier);
    }

    public DeleteResponse deleteChantier(Long chantierId) {
        Long id = chantierId;
        chantierRepository.deleteById(chantierId);
        return new DeleteResponse(id, "chantier supprimer avec succès");
    }
}
