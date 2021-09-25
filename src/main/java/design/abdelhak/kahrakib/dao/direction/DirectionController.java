package design.abdelhak.kahrakib.dao.direction;

import design.abdelhak.kahrakib.dto.requests.DirectionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class DirectionController {

    @Autowired
    private DirectionService directionService;

    @GetMapping("/directions")
    private ResponseEntity<?> getAllDirection(){
        return new ResponseEntity(directionService.getAllDirections(), HttpStatus.OK);
    }

    @GetMapping("/direction")
    private ResponseEntity<?> getDirectionById(@RequestParam Long directionId){
        return new ResponseEntity(directionService.getDirectionById(directionId),HttpStatus.OK);
    }

    @PostMapping("/direction")
    private ResponseEntity<?> saveDirection(@Valid @RequestBody DirectionRequest directionRequest){
        return new ResponseEntity(directionService.saveDirection(directionRequest),HttpStatus.OK);
    }

    @PutMapping("/direction")
    private ResponseEntity<?> updateDirection(@RequestParam Long directionId,@Valid @RequestBody DirectionEntity directionEntity){
        return new ResponseEntity(directionService.updateDirection(directionId,directionEntity),HttpStatus.OK);
    }

    @DeleteMapping("/direction")
    private ResponseEntity<?> deleteDirection(@RequestParam Long directionId){
        return new ResponseEntity(directionService.deleteDirection(directionId),HttpStatus.OK);
    }
}
