package design.abdelhak.kahrakib.dao.direction;

import design.abdelhak.kahrakib.dto.requests.DirectionRequest;
import design.abdelhak.kahrakib.dto.responses.DeleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectionService {

    @Autowired
    private DirectionRepository directionRepository;

    public List<DirectionEntity> getAllDirections() {
        return directionRepository.findAll();
    }

    public Optional<DirectionEntity> getDirectionById(Long directionId) {
        return directionRepository.findById(directionId);
    }

    public DirectionEntity saveDirection(DirectionRequest directionRequest) {
        DirectionEntity directionEntity = new DirectionEntity(
                directionRequest.getNom(),
                directionRequest.getImputation(),
                directionRequest.getAdresse()
        );
        return directionRepository.save(directionEntity);
    }

    public DirectionEntity updateDirection(Long directionId, DirectionEntity directionEntity) {
        DirectionEntity currentDirection = directionRepository.findById(directionId).get();

        currentDirection.setNom(directionEntity.getNom());
        currentDirection.setImputation(directionEntity.getImputation());
        currentDirection.setAdresse(directionEntity.getAdresse());
        return directionRepository.save(currentDirection);
    }

    public DeleteResponse deleteDirection(Long directionId) {
        Long id = directionId;
        directionRepository.deleteById(directionId);
        return new DeleteResponse(id, "direction supprimer avec succès");
    }
}
