package design.abdelhak.kahrakib.dao.direction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository extends JpaRepository<DirectionEntity,Long> {

    DirectionEntity findDirectionEntityByImputation(String imputation);
}
