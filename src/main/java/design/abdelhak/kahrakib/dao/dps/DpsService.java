package design.abdelhak.kahrakib.dao.dps;

import design.abdelhak.kahrakib.dao.achat.AchatEntity;
import design.abdelhak.kahrakib.dao.achat.AchatRepository;
import design.abdelhak.kahrakib.dao.cassier.CassierEntity;
import design.abdelhak.kahrakib.dao.client.ClientEntity;
import design.abdelhak.kahrakib.dao.client.ClientRepository;
import design.abdelhak.kahrakib.dto.requests.*;
import design.abdelhak.kahrakib.dto.responses.DeleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class DpsService {

    @Autowired
    private DpsRepository dpsRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AchatRepository achatRepository;


    public List<DpsEntity> getAllDpss() {
        return dpsRepository.findAll();
    }

    public DpsEntity getDpsById(Long dpsId) {
        return dpsRepository.findById(dpsId).get();
    }

    public DpsEntity saveDps(DpsRequest dpsRequest) {
        ClientEntity client = clientRepository.findById(dpsRequest.getClientId()).get();

        if (client == null) {
            return null;
        } else {
            CassierEntity cassier = client.getChantier().getCassier();

            DpsEntity dpsEntity = new DpsEntity(
                    client.getChantier().getImputation(),
                    dpsRequest.getPrestataire(),
                    dpsRequest.getAdressePrestataire(),
                    client,
                    cassier
            );
            DpsEntity savedDps = dpsRepository.save(dpsEntity);


            List<AchatEntity> achats = new ArrayList<>();
            BigDecimal achatMotantTotal = new BigDecimal(0);

            for (AchatWithDpsRequest achat : dpsRequest.getAchats()) {
                AchatEntity achatEntity = new AchatEntity(
                        achat.getDesignation(),
                        achat.getQuantite(),
                        achat.getPrixUnitaire(),
                        savedDps
                );
                achats.add(achatRepository.save(achatEntity));
                achatMotantTotal = achatMotantTotal.add(
                        achat.getPrixUnitaire().multiply(new BigDecimal(achat.getQuantite()))
                );
            }



            savedDps.setAchats(achats);
            savedDps.setTotalAchatMontant(achatMotantTotal);

            return dpsRepository.save(savedDps);
        }
    }

    public DpsEntity updateDpsEtatClient(Long dpsId, EtatClientRequest etatClientRequest) {
        DpsEntity dps = dpsRepository.findById(dpsId).get();
        dps.setEtatClient(etatClientRequest.getEtatClient());
        dpsRepository.save(dps);
        return dpsRepository.save(dps);
    }

    public DpsEntity updateDpsEtatCassier(Long dpsId, EtatCassierRequest etatCassierRequest) {
        DpsEntity dps = dpsRepository.findById(dpsId).get();
        dps.setEtatCassier(etatCassierRequest.getEtatCassier());
        dpsRepository.save(dps);
        return dpsRepository.save(dps);
    }

    public DeleteResponse deleteDps(Long dpsId) {
        Long id = dpsId;
        dpsRepository.deleteById(dpsId);
        return new DeleteResponse(id, "dps supprimé avec succès");
    }
}
