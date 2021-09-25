package design.abdelhak.kahrakib.dao.comptable;

import design.abdelhak.kahrakib.dto.requests.ComptableRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ComptableController {

    @Autowired
    private ComptableService comptableService;


    @GetMapping("/comptables")
    private ResponseEntity<?> getAllComptables(){
        return new ResponseEntity(comptableService.getAllComptables(), HttpStatus.OK);
    }

    @GetMapping("/comptable")
    private ResponseEntity<?> getComptableById(@RequestParam Long comptableId){
        return new ResponseEntity(comptableService.getComptableById(comptableId),HttpStatus.OK);
    }

    @PostMapping("/comptable")
    private ResponseEntity<?> saveComptable(@Valid @RequestBody ComptableRequest comptableRequest){
        return new ResponseEntity(comptableService.saveComptable(comptableRequest),HttpStatus.OK);
    }

    @PutMapping("/comptable")
    private ResponseEntity<?> updateComptable(@RequestParam Long comptableId, @Valid @RequestBody ComptableEntity comptableEntity){
        return new ResponseEntity(comptableService.updateComptable(comptableId,comptableEntity),HttpStatus.OK);
    }

    @DeleteMapping("/comptable")
    private ResponseEntity<?> deleteComptable(@RequestParam Long comptableId){
        return new ResponseEntity(comptableService.deleteComptable(comptableId),HttpStatus.OK);
    }

}
