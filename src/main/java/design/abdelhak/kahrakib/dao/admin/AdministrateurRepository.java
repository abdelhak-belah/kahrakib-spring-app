package design.abdelhak.kahrakib.dao.admin;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrateurRepository extends JpaRepository<AdministrateurEntity,Long>{
}
