package design.abdelhak.kahrakib.dao.roles;

import design.abdelhak.kahrakib.dto.responses.DeleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleEntity saveRole(RoleEntity roleEntity) {
        return roleRepository.save(roleEntity);
    }

    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<RoleEntity> getRoleById(Long roleId) {
        return roleRepository.findById(roleId);
    }

    public RoleEntity getRoleByName(String role) {
        return roleRepository.findRoleEntityByRole(role);
    }

    public RoleEntity updateRole(RoleEntity roleEntity) {
        return roleRepository.save(roleEntity);
    }

    public DeleteResponse deleteRole(Long roleId) {
        Long id = roleId;
        roleRepository.deleteById(roleId);
        return new DeleteResponse(id, "role supprimer avec succès");
    }
}
