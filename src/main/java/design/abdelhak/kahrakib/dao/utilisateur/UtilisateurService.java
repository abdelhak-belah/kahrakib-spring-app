package design.abdelhak.kahrakib.dao.utilisateur;

import design.abdelhak.kahrakib.dao.admin.AdministrateurEntity;
import design.abdelhak.kahrakib.auth.SecurityKeys;
import design.abdelhak.kahrakib.dao.admin.AdministrateurRepository;
import design.abdelhak.kahrakib.dao.cassier.CassierEntity;
import design.abdelhak.kahrakib.dao.cassier.CassierRepository;
import design.abdelhak.kahrakib.dao.cassier_respo.CassierRespoEntity;
import design.abdelhak.kahrakib.dao.cassier_respo.CassierRespoRepository;
import design.abdelhak.kahrakib.dao.client.ClientEntity;
import design.abdelhak.kahrakib.dao.client.ClientRepository;
import design.abdelhak.kahrakib.dao.comptable.ComptableEntity;
import design.abdelhak.kahrakib.dao.comptable.ComptableRepository;
import design.abdelhak.kahrakib.dao.roles.RoleService;
import design.abdelhak.kahrakib.dto.requests.MotDePasseRequest;
import design.abdelhak.kahrakib.dto.requests.RegisterRequest;
import design.abdelhak.kahrakib.dao.roles.RoleEntity;
import design.abdelhak.kahrakib.dto.responses.DeleteResponse;
import design.abdelhak.kahrakib.utils.AgeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UtilisateurService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private AdministrateurRepository administrateurRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CassierRepository cassierRepository;
    @Autowired
    private CassierRespoRepository cassierRespoRepository;
    @Autowired
    private ComptableRepository comptableRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> roles = null;

        UtilisateurEntity utilisateurEntity = utilisateurRepository.findUtilisateurEntityByEmail(email);

        if (utilisateurEntity != null) {
            roles = Arrays.asList(new SimpleGrantedAuthority(utilisateurEntity.getRole().getRole()));

            return new User(utilisateurEntity.getEmail(), utilisateurEntity.getMotDePasse(), roles);
        }
        throw new UsernameNotFoundException("Aucun utilisateur  n'existe avec l'email : " + utilisateurEntity.getEmail());
    }


    public UtilisateurEntity registerUtilisateur(RegisterRequest registerRequest) {
        RoleEntity role;

        switch (registerRequest.getRole()) {
            case SecurityKeys.ROLE_ADMIN:
                role = roleService.getRoleByName(SecurityKeys.ROLE_ADMIN);
                AdministrateurEntity administrateurEntity = new AdministrateurEntity(registerRequest.getEmail(), bcryptEncoder.encode(registerRequest.getMotDePasse()), registerRequest.getNom(), registerRequest.getPrenom(), registerRequest.getDateNaissance(), role, new Date(System.currentTimeMillis()));
                administrateurRepository.save(administrateurEntity);
                role.addUtilisateur(administrateurEntity);
                roleService.saveRole(role);
                return administrateurEntity;
            case SecurityKeys.ROLE_CLIENT:
                role = roleService.getRoleByName(SecurityKeys.ROLE_CLIENT);
                ClientEntity clientEntity = new ClientEntity(registerRequest.getEmail(), bcryptEncoder.encode(registerRequest.getMotDePasse()), registerRequest.getNom(), registerRequest.getPrenom(), registerRequest.getDateNaissance(), role, new Date(System.currentTimeMillis()));
                clientRepository.save(clientEntity);
                role.addUtilisateur(clientEntity);
                roleService.saveRole(role);
                return clientEntity;
            case SecurityKeys.ROLE_CASSIER:
                role = roleService.getRoleByName(SecurityKeys.ROLE_CASSIER);
                CassierEntity cassierEntity = new CassierEntity(registerRequest.getEmail(), bcryptEncoder.encode(registerRequest.getMotDePasse()), registerRequest.getNom(), registerRequest.getPrenom(), registerRequest.getDateNaissance(), role, new Date(System.currentTimeMillis()));
                cassierRepository.save(cassierEntity);
                role.addUtilisateur(cassierEntity);
                roleService.saveRole(role);
                return cassierEntity;
            case SecurityKeys.ROLE_CASSIER_RESPO:
                role = roleService.getRoleByName(SecurityKeys.ROLE_CASSIER_RESPO);
                CassierRespoEntity cassierRespoEntity = new CassierRespoEntity(registerRequest.getEmail(), bcryptEncoder.encode(registerRequest.getMotDePasse()), registerRequest.getNom(), registerRequest.getPrenom(), registerRequest.getDateNaissance(), role, new Date(System.currentTimeMillis()));
                cassierRespoRepository.save(cassierRespoEntity);
                role.addUtilisateur(cassierRespoEntity);
                roleService.saveRole(role);
                return cassierRespoEntity;
            case SecurityKeys.ROLE_COMPTABLE:
                role = roleService.getRoleByName(SecurityKeys.ROLE_COMPTABLE);
                ComptableEntity comptableEntity = new ComptableEntity(registerRequest.getEmail(), bcryptEncoder.encode(registerRequest.getMotDePasse()), registerRequest.getNom(), registerRequest.getPrenom(), registerRequest.getDateNaissance(), role, new Date(System.currentTimeMillis()));
                comptableRepository.save(comptableEntity);
                role.addUtilisateur(comptableEntity);
                roleService.saveRole(role);
                return comptableEntity;
            default:
                return null;
        }
    }


    public UtilisateurEntity updateMotDePasse(MotDePasseRequest motDePasseRequest) throws Exception {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(motDePasseRequest.getEmail(), motDePasseRequest.getCurrentMotDePasse()));
        }catch (DisabledException e){
            throw new Exception("USER_DISABLED",e);
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS",e);
        }

        UtilisateurEntity utilisateurEntity = utilisateurRepository.findById(motDePasseRequest.getUtilisateurId()).get();
        utilisateurEntity.setMotDePasse(bcryptEncoder.encode(motDePasseRequest.getNouveauMotDePasse()));
        return utilisateurRepository.save(utilisateurEntity);
    }

    public List<UtilisateurEntity> getAllUtilisateur() {
        return utilisateurRepository.findAll();
    }

    public UtilisateurEntity getUtilisateurById(Long utilisateurId) {
        return utilisateurRepository.findById(utilisateurId).get();
    }


    public UtilisateurEntity updateUtilisateur(Long utilisateurId, UtilisateurEntity utilisateurEntity) {

        UtilisateurEntity currentUtilisateur = utilisateurRepository.findById(utilisateurId).get();

        currentUtilisateur.setEmail(utilisateurEntity.getEmail());
        currentUtilisateur.setNom(utilisateurEntity.getNom());
        currentUtilisateur.setPrenom(utilisateurEntity.getPrenom());
        currentUtilisateur.setDateNaissance(utilisateurEntity.getDateNaissance());
        int age = AgeCalculator.avecDateNaissance(utilisateurEntity.getDateNaissance());
        currentUtilisateur.setAge(age);
        currentUtilisateur.setTelephone(utilisateurEntity.getTelephone());
        currentUtilisateur.getRole().setRole(utilisateurEntity.getRole().getRole());
        currentUtilisateur.getRole().setRoleId(utilisateurEntity.getRole().getRoleId());
        return utilisateurRepository.save(currentUtilisateur);
    }

    public DeleteResponse deleteUtilisateur(Long utilisateurId) {
        Long id = utilisateurId;
        utilisateurRepository.deleteById(utilisateurId);
        return new DeleteResponse(id, "Utilisateur supprimé avec succès");

    }

    public Long getUtilisateurId(String email) {
        return utilisateurRepository.getUtilisateurId(email);
    }

}
