package design.abdelhak.kahrakib.dao.dps;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DpsRepository extends JpaRepository<DpsEntity,Long> {

}
