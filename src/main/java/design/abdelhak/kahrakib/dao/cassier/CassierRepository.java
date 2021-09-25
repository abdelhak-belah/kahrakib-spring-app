package design.abdelhak.kahrakib.dao.cassier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CassierRepository extends JpaRepository<CassierEntity,Long> {
}
