package design.abdelhak.kahrakib.dao.edc;

import design.abdelhak.kahrakib.dao.achat.AchatEntity;
import design.abdelhak.kahrakib.dao.achat.AchatRepository;
import design.abdelhak.kahrakib.dao.cassier.CassierEntity;
import design.abdelhak.kahrakib.dao.cassier.CassierRepository;
import design.abdelhak.kahrakib.dao.cassier_respo.CassierRespoEntity;
import design.abdelhak.kahrakib.dao.comptable.ComptableEntity;
import design.abdelhak.kahrakib.dao.dps.DpsEntity;
import design.abdelhak.kahrakib.dao.dps.DpsRepository;
import design.abdelhak.kahrakib.dao.etat.EtatEntity;
import design.abdelhak.kahrakib.dao.etat.EtatKeys;
import design.abdelhak.kahrakib.dao.etat.EtatRepository;
import design.abdelhak.kahrakib.dto.requests.EdsRequest;
import design.abdelhak.kahrakib.dto.requests.EtatEdsRequest;
import design.abdelhak.kahrakib.dto.responses.DeleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class EdsService {

    @Autowired
    private EdsRepository edsRepository;

    @Autowired
    private CassierRepository cassierRepository;

    @Autowired
    private EtatRepository etatRepository;

    @Autowired
    private DpsRepository dpsRepository;

    @Autowired
    private AchatRepository achatRepository;

    public List<EdsEntity> getAllEds() {
        return edsRepository.findAll();
    }

    public EdsEntity getEdsById(Long edsId) {
        return edsRepository.findById(edsId).get();
    }

    public EdsEntity saveEds(EdsRequest edsRequest) {
        CassierEntity cassier = cassierRepository.findById(edsRequest.getCassierId()).get();

        if (cassier == null) {
            return null;
        } else {
            CassierRespoEntity cassierRespo = cassier.getChantier().getCassierRespo();
            ComptableEntity comptable = cassier.getChantier().getDirection().getComptable();
            EtatEntity etat = etatRepository.findEtatEntityByEtat(EtatKeys.ETAT_NON_VALID_CAISSIER);

            //get all DPSs
            List<DpsEntity> dpss = new ArrayList<>();
            for (Long dpsId : edsRequest.getDpsId()) {
                DpsEntity dpsEntity = dpsRepository.findById(dpsId).get();
                if (dpsEntity != null) {
                    dpsEntity.setEtatCassier(true);
                    dpss.add(dpsEntity);
                }
            }

            //calculate montant global
            BigDecimal montantGlobal = new BigDecimal(0);
            for (DpsEntity dps : dpss) {
                for (AchatEntity achat : dps.getAchats()) {
                    montantGlobal = montantGlobal.add(
                            achat.getPrixUnitaire().multiply(
                                    new BigDecimal(achat.getQuantite())
                            )
                    );
                }
            }

            //check caissier budget
            if (montantGlobal.compareTo(cassier.getBudget()) > 0) {
                return null;
            } else {
                //save edc
                EdsEntity edsEntity = new EdsEntity(cassier.getChantier().getImputation(), montantGlobal, comptable, cassier, cassierRespo, dpss, etat);
                EdsEntity savedEds = edsRepository.save(edsEntity);

                //update dps fk
                for (DpsEntity dps : dpss) {
                    dps.setEds(savedEds);
                    dpsRepository.save(dps);
                }


                return savedEds;
            }
        }
    }

    public EdsEntity updateEtatEds(Long edsId, EtatEdsRequest etatEdsRequest) {
        EdsEntity eds = edsRepository.findById(edsId).get();
        EtatEntity etat = etatRepository.findEtatEntityByEtat(etatEdsRequest.getEtatEds());
        CassierEntity cassier = eds.getCassier();

        eds.setEtat(etat);

        if (etat.getEtat().equals(EtatKeys.ETAT_VALID_COMPTABLE)) {
            cassier.setBudget(cassier.getBudget().add(eds.getMontantGlobal()));
            cassierRepository.save(cassier);
        }

        if (etat.getEtat().equals(EtatKeys.ETAT_VALID_CAISSIER)) {
            if (cassier.getBudget().compareTo(eds.getMontantGlobal()) < 0) {
                return null;
            } else {
                cassier = eds.getCassier();
                cassier.setBudget(cassier.getBudget().subtract(eds.getMontantGlobal()));
                cassierRepository.save(cassier);
            }
        }
        return edsRepository.save(eds);
    }


    public DeleteResponse deleteEds(Long edsId) {
        Long id = edsId;

        EdsEntity eds = edsRepository.findById(edsId).get();
        List<DpsEntity> dpss = eds.getDpss();

        CassierEntity cassier = cassierRepository.findById(eds.getCassier().getUtilisateurId()).get();

        for (DpsEntity dps : dpss) {
            dps.setEds(null);
            dps.setEtatCassier(false);
            dpsRepository.save(dps);
        }
        eds.setDpss(null);
        edsRepository.deleteById(edsId);
        return new DeleteResponse(id, "edc supprimer avec succès");
    }
}
