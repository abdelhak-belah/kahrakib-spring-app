package design.abdelhak.kahrakib.dao.elements;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementRepository extends JpaRepository<ElementEntity,Long> {
}
