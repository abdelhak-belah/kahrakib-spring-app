package design.abdelhak.kahrakib.dao.etat;

import design.abdelhak.kahrakib.dto.requests.EtatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class EtatController {

    @Autowired
    private EtatService etatService;

    @GetMapping("/etats")
    private ResponseEntity<?> getAllEtats(){
        return new ResponseEntity(etatService.getAllEtats(), HttpStatus.OK);
    }

    @GetMapping("/etat")
    private ResponseEntity<?> getEtatById(@RequestParam Long etatId){
        return new ResponseEntity(etatService.getEtatById(etatId),HttpStatus.OK);
    }

    @PostMapping("/etat")
    private ResponseEntity<?> saveEtat(@Valid @RequestBody EtatRequest etatRequest){
        return new ResponseEntity(etatService.saveEtat(etatRequest),HttpStatus.OK);
    }

    @PutMapping("/etat")
    private ResponseEntity<?> updateEtat(@RequestParam Long etatId, @Valid @RequestBody EtatEntity etatEntity){
        return new ResponseEntity(etatService.updateEtat(etatId,etatEntity),HttpStatus.OK);
    }

    @DeleteMapping("/etat")
    private ResponseEntity<?> deleteEtat(@RequestParam Long etatId){
        return new ResponseEntity(etatService.deleteEtat(etatId),HttpStatus.OK);
    }
}
