package design.abdelhak.kahrakib.dao.admin;

import design.abdelhak.kahrakib.dto.requests.AdministrateurRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AdministrateurController {

    @Autowired
    private AdministrateurService administrateurService;


    @GetMapping("/admins")
    private ResponseEntity<?> getAllAdmins(){
        return new ResponseEntity(administrateurService.getAllAdmins(), HttpStatus.OK);
    }

    @GetMapping("/admin")
    private ResponseEntity<?> getAdminById(@RequestParam Long adminId){
        return new ResponseEntity(administrateurService.getAdminById(adminId),HttpStatus.OK);
    }

    @PostMapping("/admin")
    private ResponseEntity<?> saveAdmin(@Valid @RequestBody AdministrateurRequest administrateurRequest){
        return new ResponseEntity(administrateurService.saveAdmin(administrateurRequest),HttpStatus.OK);
    }

    @PutMapping("/admin")
    private ResponseEntity<?> updateAdmin(@RequestParam Long adminId, @Valid @RequestBody AdministrateurEntity administrateurEntity){
        return new ResponseEntity(administrateurService.updateAdmin(adminId,administrateurEntity),HttpStatus.OK);
    }

    @DeleteMapping("/admin")
    private ResponseEntity<?> deleteAdmin(@RequestParam Long adminId){
        return new ResponseEntity(administrateurService.deleteAdmin(adminId),HttpStatus.OK);
    }
}
