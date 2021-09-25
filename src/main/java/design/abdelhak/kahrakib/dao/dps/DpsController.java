package design.abdelhak.kahrakib.dao.dps;

import design.abdelhak.kahrakib.dto.requests.DpsRequest;
import design.abdelhak.kahrakib.dto.requests.EtatCassierRequest;
import design.abdelhak.kahrakib.dto.requests.EtatClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class DpsController {

    @Autowired
    private DpsService dpsService;

    @GetMapping("/dpss")
    private ResponseEntity<?> getAllDpss(){
        return new ResponseEntity(dpsService.getAllDpss(), HttpStatus.OK);
    }

    @GetMapping("/dps")
    private ResponseEntity<?> getDpsById(@RequestParam Long dpsId){
        return new ResponseEntity(dpsService.getDpsById(dpsId),HttpStatus.OK);
    }

    @PostMapping("/dps")
    private ResponseEntity<?> saveDps(@Valid @RequestBody DpsRequest dpsRequest){
        return new ResponseEntity(dpsService.saveDps(dpsRequest),HttpStatus.OK);
    }

    @PutMapping("/dpsEtatClient")
    private ResponseEntity<?> updateDpsEtatClient(@RequestParam Long dpsId, @Valid @RequestBody EtatClientRequest etatClientRequest){
        return new ResponseEntity(dpsService.updateDpsEtatClient(dpsId, etatClientRequest),HttpStatus.OK);
    }

    @PutMapping("/dpsEtatCassier")
    private ResponseEntity<?> updateDpsEtatCassier(@RequestParam Long dpsId, @Valid @RequestBody EtatCassierRequest etatCassierRequest){
        return new ResponseEntity(dpsService.updateDpsEtatCassier(dpsId, etatCassierRequest),HttpStatus.OK);
    }

    @DeleteMapping("/dps")
    private ResponseEntity<?> deleteDps(@RequestParam Long dpsId){
        return new ResponseEntity(dpsService.deleteDps(dpsId),HttpStatus.OK);
    }
}
