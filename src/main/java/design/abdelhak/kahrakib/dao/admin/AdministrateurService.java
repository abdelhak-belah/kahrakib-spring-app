package design.abdelhak.kahrakib.dao.admin;


import design.abdelhak.kahrakib.auth.SecurityKeys;
import design.abdelhak.kahrakib.dao.roles.RoleEntity;
import design.abdelhak.kahrakib.dao.roles.RoleRepository;
import design.abdelhak.kahrakib.dto.requests.AdministrateurRequest;
import design.abdelhak.kahrakib.dto.responses.DeleteResponse;
import design.abdelhak.kahrakib.utils.AgeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdministrateurService {

    @Autowired
    private AdministrateurRepository administrateurRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public List<AdministrateurEntity> getAllAdmins() {
        return administrateurRepository.findAll();
    }

    public Optional<AdministrateurEntity> getAdminById(Long adminId) {
        return administrateurRepository.findById(adminId);
    }

    public AdministrateurEntity saveAdmin(AdministrateurRequest administrateurRequest) {
        RoleEntity role = roleRepository.findRoleEntityByRole(SecurityKeys.ROLE_ADMIN);

        AdministrateurEntity administrateurEntity = new AdministrateurEntity(administrateurRequest.getEmail(), bcryptEncoder.encode(administrateurRequest.getMotDePasse()), administrateurRequest.getNom(), administrateurRequest.getPrenom(), administrateurRequest.getDateNaissance(), role, new Date(System.currentTimeMillis()));
        administrateurRepository.save(administrateurEntity);
        role.addUtilisateur(administrateurEntity);
        roleRepository.save(role);
        return administrateurEntity;
    }

    public AdministrateurEntity updateAdmin(Long adminId, AdministrateurEntity administrateurEntity) {

        AdministrateurEntity currentAdministrateur = administrateurRepository.findById(adminId).get();

        currentAdministrateur.setEmail(administrateurEntity.getEmail());
        currentAdministrateur.setNom(administrateurEntity.getNom());
        currentAdministrateur.setPrenom(administrateurEntity.getPrenom());
        currentAdministrateur.setDateNaissance(administrateurEntity.getDateNaissance());
        int age = AgeCalculator.avecDateNaissance(administrateurEntity.getDateNaissance());
        currentAdministrateur.setAge(age);
        currentAdministrateur.setTelephone(administrateurEntity.getTelephone());
        currentAdministrateur.getRole().setRole(administrateurEntity.getRole().getRole());
        currentAdministrateur.getRole().setRoleId(administrateurEntity.getRole().getRoleId());
        return administrateurRepository.save(currentAdministrateur);
    }


    public DeleteResponse deleteAdmin(Long adminId) {
        Long id = adminId;
        administrateurRepository.deleteById(adminId);
        return new DeleteResponse(id, "administrateur supprimer avec succès");
    }


}
