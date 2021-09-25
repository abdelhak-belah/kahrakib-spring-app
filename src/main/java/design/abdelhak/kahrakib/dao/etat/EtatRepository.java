package design.abdelhak.kahrakib.dao.etat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtatRepository extends JpaRepository<EtatEntity,Long> {

    EtatEntity findEtatEntityByEtat(String etat);
}
