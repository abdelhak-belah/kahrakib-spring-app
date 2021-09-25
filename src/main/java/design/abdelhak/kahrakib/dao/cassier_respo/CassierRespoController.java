package design.abdelhak.kahrakib.dao.cassier_respo;

import design.abdelhak.kahrakib.dto.requests.CassierRespoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CassierRespoController {

    @Autowired
    private CassierRespoService cassierRespoService;

    @GetMapping("/cassiersRespo")
    private ResponseEntity<?> getAllCassiersRespo(){
        return new ResponseEntity(cassierRespoService.getAllCassiersRespo(), HttpStatus.OK);
    }

    @GetMapping("/cassierRespo")
    private ResponseEntity<?> getCassierRespoById(@RequestParam Long cassierRespoId){
        return new ResponseEntity(cassierRespoService.getCassierRespoById(cassierRespoId),HttpStatus.OK);
    }

    @PostMapping("/cassierRespo")
    private ResponseEntity<?> saveCassierRespo(@Valid @RequestBody CassierRespoRequest cassierRespoRequest){
        return new ResponseEntity(cassierRespoService.saveCassierRespo(cassierRespoRequest),HttpStatus.OK);
    }

    @PutMapping("/cassierRespo")
    private ResponseEntity<?> updateCassierRespo(@RequestParam Long cassierRespoId, @Valid @RequestBody CassierRespoEntity cassierRespoEntity){
        return new ResponseEntity(cassierRespoService.updateCassierRespo(cassierRespoId,cassierRespoEntity),HttpStatus.OK);
    }

    @DeleteMapping("/cassierRespo")
    private ResponseEntity<?> deleteCassierRespo(@RequestParam Long cassierRespoId){
        return new ResponseEntity(cassierRespoService.deleteCassierRespo(cassierRespoId),HttpStatus.OK);
    }

}
