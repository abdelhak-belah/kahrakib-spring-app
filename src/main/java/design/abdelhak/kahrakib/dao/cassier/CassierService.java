package design.abdelhak.kahrakib.dao.cassier;

import design.abdelhak.kahrakib.auth.SecurityKeys;
import design.abdelhak.kahrakib.dao.chantier.ChantierEntity;
import design.abdelhak.kahrakib.dao.chantier.ChantierRepository;
import design.abdelhak.kahrakib.dao.roles.RoleEntity;
import design.abdelhak.kahrakib.dao.roles.RoleRepository;
import design.abdelhak.kahrakib.dto.requests.CassierRequest;
import design.abdelhak.kahrakib.dto.responses.DeleteResponse;
import design.abdelhak.kahrakib.utils.AgeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CassierService {

    @Autowired
    private CassierRepository cassierRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ChantierRepository chantierRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;


    public List<CassierEntity> getAllCassiers() {
        return cassierRepository.findAll();
    }

    public CassierEntity getCassierById(Long cassierId) {
        return cassierRepository.findById(cassierId).get();
    }

    public CassierEntity saveCassier(CassierRequest cassierRequest) {
        RoleEntity role = roleRepository.findRoleEntityByRole(SecurityKeys.ROLE_CASSIER);
        ChantierEntity chantier = chantierRepository.findChantierEntitiesByImputation(cassierRequest.getChantierImputation());

        if(chantier.getCassier() != null){
            return null;
        }else {
            CassierEntity cassierEntity = new CassierEntity(cassierRequest.getEmail(), bcryptEncoder.encode(cassierRequest.getMotDePasse()), cassierRequest.getNom(), cassierRequest.getPrenom(), cassierRequest.getDateNaissance(), role, new Date(System.currentTimeMillis()), chantier);
            cassierRepository.save(cassierEntity);
            role.addUtilisateur(cassierEntity);
            roleRepository.save(role);
            return cassierEntity;
        }
    }

    public CassierEntity updateCassier(Long cassierId, CassierEntity cassierEntity) {
        CassierEntity currentCassier = cassierRepository.findById(cassierId).get();

        ChantierEntity chantier = chantierRepository.findChantierEntitiesByImputation(cassierEntity.getChantier().getImputation());

        if (chantier.getCassier() != null){
            return null;
        }else {
            currentCassier.setEmail(cassierEntity.getEmail());
            currentCassier.setNom(cassierEntity.getNom());
            currentCassier.setPrenom(cassierEntity.getPrenom());
            currentCassier.setDateNaissance(cassierEntity.getDateNaissance());
            int age = AgeCalculator.avecDateNaissance(cassierEntity.getDateNaissance());
            currentCassier.setAge(age);
            currentCassier.setTelephone(cassierEntity.getTelephone());
            currentCassier.getRole().setRole(cassierEntity.getRole().getRole());
            currentCassier.getRole().setRoleId(cassierEntity.getRole().getRoleId());
            currentCassier.setChantier(cassierEntity.getChantier());
            return cassierRepository.save(currentCassier);
        }
    }

    public DeleteResponse deleteCassier(Long cassierId) {
        Long id = cassierId;
        cassierRepository.deleteById(cassierId);
        return new DeleteResponse(id, "cassier supprimer avec succès");
    }
}
