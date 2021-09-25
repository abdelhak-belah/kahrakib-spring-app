package design.abdelhak.kahrakib.dao.achat;

import design.abdelhak.kahrakib.dto.requests.AchatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AchatController {

    @Autowired
    private AchatService achatService;

    @GetMapping("/achats")
    private ResponseEntity<?> getAllAchats(){
        return new ResponseEntity(achatService.getAllAchats(), HttpStatus.OK);
    }

    @GetMapping("/achat")
    private ResponseEntity<?> getAchatById(@RequestParam Long achatId){
        return new ResponseEntity(achatService.getAchatById(achatId),HttpStatus.OK);
    }

    @PostMapping("/achat")
    private ResponseEntity<?> saveAchat(@Valid @RequestBody AchatRequest achatRequest){
        return new ResponseEntity(achatService.saveAchat(achatRequest),HttpStatus.OK);
    }

    @PutMapping("/achat")
    private ResponseEntity<?> updateAchat(@RequestParam Long achatId, @Valid @RequestBody AchatEntity achatEntity){
        return new ResponseEntity(achatService.updateAchat(achatId,achatEntity),HttpStatus.OK);
    }

    @DeleteMapping("/achat")
    private ResponseEntity<?> deleteAchat(@RequestParam Long achatId){
        return new ResponseEntity(achatService.deleteAchat(achatId),HttpStatus.OK);
    }

}
