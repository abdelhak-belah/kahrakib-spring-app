package design.abdelhak.kahrakib.dao.edc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdsRepository extends JpaRepository<EdsEntity,Long> {
}
