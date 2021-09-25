package design.abdelhak.kahrakib.dao.comptable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComptableRepository extends JpaRepository<ComptableEntity,Long> {
}
