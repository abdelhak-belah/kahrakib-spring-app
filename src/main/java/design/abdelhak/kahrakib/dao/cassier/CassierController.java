package design.abdelhak.kahrakib.dao.cassier;

import design.abdelhak.kahrakib.dto.requests.CassierRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CassierController {

    @Autowired
    private CassierService cassierService;

    @GetMapping("/cassiers")
    private ResponseEntity<?> getAllCassiers(){
        return new ResponseEntity(cassierService.getAllCassiers(), HttpStatus.OK);
    }

    @GetMapping("/cassier")
    private ResponseEntity<?> getCassierById(@RequestParam Long cassierId){
        return new ResponseEntity(cassierService.getCassierById(cassierId),HttpStatus.OK);
    }

    @PostMapping("/cassier")
    private ResponseEntity<?> saveCassier(@Valid @RequestBody CassierRequest cassierRequest){
        return new ResponseEntity(cassierService.saveCassier(cassierRequest),HttpStatus.OK);
    }

    @PutMapping("/cassier")
    private ResponseEntity<?> updateCassier(@RequestParam Long cassierId, @Valid @RequestBody CassierEntity cassierEntity){
        return new ResponseEntity(cassierService.updateCassier(cassierId,cassierEntity),HttpStatus.OK);
    }

    @DeleteMapping("/cassier")
    private ResponseEntity<?> deleteCassier(@RequestParam Long cassierId){
        return new ResponseEntity(cassierService.deleteCassier(cassierId),HttpStatus.OK);
    }
}
