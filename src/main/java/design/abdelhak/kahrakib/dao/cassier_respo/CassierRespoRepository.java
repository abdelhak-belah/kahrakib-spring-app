package design.abdelhak.kahrakib.dao.cassier_respo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CassierRespoRepository extends JpaRepository<CassierRespoEntity,Long> {
}
