package design.abdelhak.kahrakib.dao.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    private ResponseEntity<?> getAllRoles(){
        return new ResponseEntity(roleService.getAllRoles(),HttpStatus.OK);
    }


    @GetMapping("/role")
    private ResponseEntity<?> getRoleById(@RequestParam Long roleId){
        return new ResponseEntity(roleService.getRoleById(roleId),HttpStatus.OK);
    }

    @PostMapping("/role")
    private ResponseEntity<?> saveRole(@RequestBody RoleEntity roleEntity){
        return new ResponseEntity(roleService.saveRole(roleEntity), HttpStatus.OK);
    }

    @PutMapping("/role")
    private ResponseEntity<?> updateRole(@RequestBody RoleEntity roleEntity){
        return new ResponseEntity(roleService.updateRole(roleEntity),HttpStatus.OK);
    }

    @DeleteMapping("/role")
    private ResponseEntity<?> deleteRole(@RequestParam Long roleId){
        return new ResponseEntity(roleService.deleteRole(roleId),HttpStatus.OK);
    }
}
