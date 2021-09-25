package design.abdelhak.kahrakib.dao.client;

import design.abdelhak.kahrakib.auth.SecurityKeys;
import design.abdelhak.kahrakib.dao.chantier.ChantierEntity;
import design.abdelhak.kahrakib.dao.chantier.ChantierRepository;
import design.abdelhak.kahrakib.dao.roles.RoleEntity;
import design.abdelhak.kahrakib.dao.roles.RoleRepository;
import design.abdelhak.kahrakib.dto.requests.ClientRequest;
import design.abdelhak.kahrakib.dto.responses.DeleteResponse;
import design.abdelhak.kahrakib.utils.AgeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private ChantierRepository chantierRepository;

    public List<ClientEntity> getAllClients() {
        return clientRepository.findAll();
    }

    public ClientEntity getClientById(Long clientId) {
        return clientRepository.findById(clientId).get();
    }

    public ClientEntity saveClient(ClientRequest clientRequest) {
        RoleEntity role = roleRepository.findRoleEntityByRole(SecurityKeys.ROLE_CLIENT);
        ChantierEntity chantier = chantierRepository.findChantierEntitiesByImputation(clientRequest.getChantierImputation());

        ClientEntity clientEntity = new ClientEntity(
                clientRequest.getEmail(),
                bcryptEncoder.encode(clientRequest.getMotDePasse()),
                clientRequest.getNom(),
                clientRequest.getPrenom(),
                clientRequest.getDateNaissance(),
                role,
                new Date(System.currentTimeMillis()),
                chantier
        );
        clientRepository.save(clientEntity);
        role.addUtilisateur(clientEntity);
        roleRepository.save(role);
        return clientEntity;
    }

    public ClientEntity updateClient(Long clientId, ClientEntity clientEntity) {
        ClientEntity currentClient = clientRepository.findById(clientId).get();

        currentClient.setEmail(clientEntity.getEmail());
        currentClient.setNom(clientEntity.getNom());
        currentClient.setPrenom(clientEntity.getPrenom());
        currentClient.setDateNaissance(clientEntity.getDateNaissance());
        int age = AgeCalculator.avecDateNaissance(clientEntity.getDateNaissance());
        currentClient.setAge(age);
        currentClient.setTelephone(clientEntity.getTelephone());
        currentClient.getRole().setRole(clientEntity.getRole().getRole());
        currentClient.getRole().setRoleId(clientEntity.getRole().getRoleId());
        currentClient.setChantier(clientEntity.getChantier());
        return clientRepository.save(currentClient);
    }

    public DeleteResponse deleteClient(Long clientId) {
        Long id = clientId;
        clientRepository.deleteById(clientId);
        return new DeleteResponse(id, "client supprimer avec succès");
    }

}
