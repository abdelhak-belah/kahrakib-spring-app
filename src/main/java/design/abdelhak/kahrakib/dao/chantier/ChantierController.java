package design.abdelhak.kahrakib.dao.chantier;

import design.abdelhak.kahrakib.dto.requests.ChantierRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ChantierController {

    @Autowired
    private ChantierService chantierService;

    @GetMapping("/chantiers")
    private ResponseEntity<?> getAllChantiers(){
        return new ResponseEntity(chantierService.getAllChantiers(), HttpStatus.OK);
    }

    @GetMapping("/chantier")
    private ResponseEntity<?> getChantierById(@RequestParam Long chantierId){
        return new ResponseEntity(chantierService.getChantierById(chantierId),HttpStatus.OK);
    }

    @PostMapping("/chantier")
    private ResponseEntity<?> saveChantier(@Valid @RequestBody ChantierRequest chantierRequest){
        return new ResponseEntity(chantierService.saveChantier(chantierRequest),HttpStatus.OK);
    }

    @PutMapping("/chantier")
    private ResponseEntity<?> updateChantier(@RequestParam Long chantierId,@Valid @RequestBody ChantierRequest chantierRequest){
        return new ResponseEntity(chantierService.updateChantier(chantierId,chantierRequest),HttpStatus.OK);
    }

    @DeleteMapping("/chantier")
    private ResponseEntity<?> deleteChantier(@RequestParam Long chantierId){
        return new ResponseEntity(chantierService.deleteChantier(chantierId),HttpStatus.OK);
    }
}
