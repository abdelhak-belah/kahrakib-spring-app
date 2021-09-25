package design.abdelhak.kahrakib.dao.admin;

import design.abdelhak.kahrakib.dao.utilisateur.UtilisateurEntity;
import design.abdelhak.kahrakib.dao.roles.RoleEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "administrateurs")
public class AdministrateurEntity extends UtilisateurEntity{

    public AdministrateurEntity() {
    }

    public AdministrateurEntity(String email, String motDePasse, String nom, String prenom,Date dateNaissance,RoleEntity role, Date dateCreation) {
        super(email, motDePasse, nom, prenom,dateNaissance,role, dateCreation);
    }

    public AdministrateurEntity(String email, String motDePasse, String nom, String prenom, Date dateNaissance, int age, String telephone, RoleEntity role, Date dateCreation) {
        super(email, motDePasse, nom, prenom, dateNaissance, age, telephone, role, dateCreation);
    }


}
