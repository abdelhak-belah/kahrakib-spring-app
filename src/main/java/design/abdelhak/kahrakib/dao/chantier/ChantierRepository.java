package design.abdelhak.kahrakib.dao.chantier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChantierRepository extends JpaRepository<ChantierEntity,Long> {

    ChantierEntity findChantierEntitiesByImputation(String imputation);
}
