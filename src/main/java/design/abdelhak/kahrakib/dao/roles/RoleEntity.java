package design.abdelhak.kahrakib.dao.roles;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import design.abdelhak.kahrakib.dao.utilisateur.UtilisateurEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @NotEmpty
    @Column(unique = true)
    private String role;


    @JsonIgnoreProperties("role")
    @OneToMany(mappedBy = "role",cascade = CascadeType.REMOVE)
    private List<UtilisateurEntity> utilisateurs;

    public RoleEntity() {
    }

    public RoleEntity(String role) {
        this.role = role;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<UtilisateurEntity> getUtilisateurs() {
        return utilisateurs;
    }

    public void addUtilisateur(UtilisateurEntity utilisateur) {
        this.utilisateurs.add(utilisateur);
    }
}
