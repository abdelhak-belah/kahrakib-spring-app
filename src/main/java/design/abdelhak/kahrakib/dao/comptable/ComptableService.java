package design.abdelhak.kahrakib.dao.comptable;

import design.abdelhak.kahrakib.auth.SecurityKeys;
import design.abdelhak.kahrakib.dao.direction.DirectionEntity;
import design.abdelhak.kahrakib.dao.direction.DirectionRepository;
import design.abdelhak.kahrakib.dao.roles.RoleEntity;
import design.abdelhak.kahrakib.dao.roles.RoleRepository;
import design.abdelhak.kahrakib.dto.requests.ComptableRequest;
import design.abdelhak.kahrakib.dto.responses.DeleteResponse;
import design.abdelhak.kahrakib.utils.AgeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ComptableService {

    @Autowired
    private ComptableRepository comptableRepository;

    @Autowired
    private DirectionRepository directionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public List<ComptableEntity> getAllComptables() {
        return comptableRepository.findAll();
    }

    public ComptableEntity getComptableById(Long comptableId) {
        return comptableRepository.findById(comptableId).get();
    }

    public ComptableEntity saveComptable(ComptableRequest comptableRequest) {

        RoleEntity role = roleRepository.findRoleEntityByRole(SecurityKeys.ROLE_COMPTABLE);
        DirectionEntity direction = directionRepository.findDirectionEntityByImputation(comptableRequest.getDirectionImputation());

        if (direction.getComptable() != null){
            return null;
        }else {
            ComptableEntity comptableEntity = new ComptableEntity(comptableRequest.getEmail(), bcryptEncoder.encode(comptableRequest.getMotDePasse()), comptableRequest.getNom(), comptableRequest.getPrenom(), comptableRequest.getDateNaissance(), role, new Date(System.currentTimeMillis()), direction);
            comptableRepository.save(comptableEntity);
            role.addUtilisateur(comptableEntity);
            roleRepository.save(role);
            return comptableEntity;
        }
    }

    public ComptableEntity updateComptable(Long comptableId, ComptableEntity comptableEntity) {
        ComptableEntity currentComptable = comptableRepository.findById(comptableId).get();

        DirectionEntity direction = directionRepository.findDirectionEntityByImputation(comptableEntity.getDirection().getImputation());
        if (direction.getComptable() != null ){
            return null;
        }else {
            currentComptable.setEmail(comptableEntity.getEmail());
            currentComptable.setNom(comptableEntity.getNom());
            currentComptable.setPrenom(comptableEntity.getPrenom());
            currentComptable.setDateNaissance(comptableEntity.getDateNaissance());
            int age = AgeCalculator.avecDateNaissance(comptableEntity.getDateNaissance());
            currentComptable.setAge(age);
            currentComptable.setTelephone(comptableEntity.getTelephone());
            currentComptable.setDirection(comptableEntity.getDirection());
            currentComptable.getRole().setRole(comptableEntity.getRole().getRole());
            currentComptable.getRole().setRoleId(comptableEntity.getRole().getRoleId());
            return comptableRepository.save(currentComptable);
        }
    }

    public DeleteResponse deleteComptable(Long comptableId) {
        Long id = comptableId;
        comptableRepository.deleteById(comptableId);
        return new DeleteResponse(id, "comptable supprimer avec succès");
    }


}
