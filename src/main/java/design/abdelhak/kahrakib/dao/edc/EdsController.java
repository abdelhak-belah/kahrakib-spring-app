package design.abdelhak.kahrakib.dao.edc;

import design.abdelhak.kahrakib.dto.requests.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class EdsController {

    @Autowired
    private EdsService edsService;

    @GetMapping("/edss")
    private ResponseEntity<?> getAllEdss(){
        return new ResponseEntity(edsService.getAllEds(), HttpStatus.OK);
    }

    @GetMapping("/eds")
    private ResponseEntity<?> getEdsById(@RequestParam Long edsId){
        return new ResponseEntity(edsService.getEdsById(edsId),HttpStatus.OK);
    }

    @PostMapping("/eds")
    private ResponseEntity<?> saveEds(@Valid @RequestBody EdsRequest edsRequest){
        return new ResponseEntity(edsService.saveEds(edsRequest),HttpStatus.OK);
    }

    @PutMapping("/edsEtat")
    private ResponseEntity<?> updateEdsEtat(@RequestParam Long edsId, @Valid @RequestBody EtatEdsRequest etatEdsRequest){
        return new ResponseEntity(edsService.updateEtatEds(edsId, etatEdsRequest),HttpStatus.OK);
    }

    @DeleteMapping("/eds")
    private ResponseEntity<?> deleteEds(@RequestParam Long edsId){
        return new ResponseEntity(edsService.deleteEds(edsId),HttpStatus.OK);
    }
}
