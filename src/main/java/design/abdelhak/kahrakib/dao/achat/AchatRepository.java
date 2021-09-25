package design.abdelhak.kahrakib.dao.achat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchatRepository extends JpaRepository<AchatEntity,Long> {
}
