package design.abdelhak.kahrakib.dao.cassier_respo;

import design.abdelhak.kahrakib.auth.SecurityKeys;
import design.abdelhak.kahrakib.dao.chantier.ChantierEntity;
import design.abdelhak.kahrakib.dao.chantier.ChantierRepository;
import design.abdelhak.kahrakib.dao.roles.RoleEntity;
import design.abdelhak.kahrakib.dao.roles.RoleRepository;
import design.abdelhak.kahrakib.dto.requests.CassierRespoRequest;
import design.abdelhak.kahrakib.dto.responses.DeleteResponse;
import design.abdelhak.kahrakib.utils.AgeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CassierRespoService {

    @Autowired
    private CassierRespoRepository cassierRespoRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ChantierRepository chantierRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public List<CassierRespoEntity> getAllCassiersRespo() {
        return cassierRespoRepository.findAll();
    }

    public CassierRespoEntity getCassierRespoById(Long cassierRespoId) {
        return cassierRespoRepository.findById(cassierRespoId).get();
    }

    public CassierRespoEntity saveCassierRespo(CassierRespoRequest cassierRespoRequest) {
        RoleEntity role = roleRepository.findRoleEntityByRole(SecurityKeys.ROLE_CASSIER_RESPO);
        ChantierEntity chantier = chantierRepository.findChantierEntitiesByImputation(cassierRespoRequest.getChantierImputation());

        if (chantier.getCassierRespo() != null){
            return null;
        }else {
            CassierRespoEntity cassierRespoEntity = new CassierRespoEntity(cassierRespoRequest.getEmail(), bcryptEncoder.encode(cassierRespoRequest.getMotDePasse()), cassierRespoRequest.getNom(), cassierRespoRequest.getPrenom(), cassierRespoRequest.getDateNaissance(), role, new Date(System.currentTimeMillis()), chantier);
            cassierRespoRepository.save(cassierRespoEntity);
            role.addUtilisateur(cassierRespoEntity);
            roleRepository.save(role);
            return cassierRespoEntity;
        }
    }

    public CassierRespoEntity updateCassierRespo(Long cassierRespoId, CassierRespoEntity cassierRespoEntity) {
        CassierRespoEntity currentCassierRespo = cassierRespoRepository.findById(cassierRespoId).get();

        ChantierEntity chantier = chantierRepository.findChantierEntitiesByImputation(cassierRespoEntity.getChantier().getImputation());

        if (chantier.getCassierRespo() != null){
            return null;
        }else {
            currentCassierRespo.setEmail(cassierRespoEntity.getEmail());
            currentCassierRespo.setNom(cassierRespoEntity.getNom());
            currentCassierRespo.setPrenom(cassierRespoEntity.getPrenom());
            currentCassierRespo.setDateNaissance(cassierRespoEntity.getDateNaissance());
            int age = AgeCalculator.avecDateNaissance(cassierRespoEntity.getDateNaissance());
            currentCassierRespo.setAge(age);
            currentCassierRespo.setTelephone(cassierRespoEntity.getTelephone());
            currentCassierRespo.getRole().setRole(cassierRespoEntity.getRole().getRole());
            currentCassierRespo.getRole().setRoleId(cassierRespoEntity.getRole().getRoleId());
            currentCassierRespo.setChantier(cassierRespoEntity.getChantier());
            return cassierRespoRepository.save(currentCassierRespo);
        }
    }

    public DeleteResponse deleteCassierRespo(Long cassierRespoId) {
        Long id = cassierRespoId;
        cassierRespoRepository.deleteById(cassierRespoId);
        return new DeleteResponse(id, "cassier responsable supprimer avec succès");
    }
}
