package design.abdelhak.kahrakib.dao.elements;

import design.abdelhak.kahrakib.dto.requests.ElementRequest;
import design.abdelhak.kahrakib.dto.responses.DeleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementService {

    @Autowired
    private ElementRepository elementRepository;

    public List<ElementEntity> getAllElements() {
        return elementRepository.findAll();
    }

    public ElementEntity getElementById(Long elementId) {
        return elementRepository.findById(elementId).get();
    }

    public ElementEntity saveElement(ElementRequest elementRequest) {
        ElementEntity elementEntity = new ElementEntity(elementRequest.getNom());
        return elementRepository.save(elementEntity);
    }

    public ElementEntity updateElement(Long elementId, ElementEntity elementEntity) {
        ElementEntity currentElement = elementRepository.findById(elementId).get();
        currentElement.setNom(elementEntity.getNom());
        return elementRepository.save(currentElement);
    }

    public DeleteResponse deleteElement(Long elementId) {
        Long id = elementId;
        elementRepository.deleteById(elementId);
        return new DeleteResponse(id, "element supprimé avec succès");
    }
}
