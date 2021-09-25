package design.abdelhak.kahrakib.dao.utilisateur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<UtilisateurEntity,Long> {


    UtilisateurEntity findUtilisateurEntityByEmail(String email);

    @Query(value = "SELECT utilisateur_id FROM utilisateurs WHERE email = ?1",nativeQuery = true)
    Long getUtilisateurId(String email);




}
