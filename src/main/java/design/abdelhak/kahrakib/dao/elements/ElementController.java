package design.abdelhak.kahrakib.dao.elements;

import design.abdelhak.kahrakib.dto.requests.ElementRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ElementController {

    @Autowired
    private ElementService elementService;

    @GetMapping("/elements")
    private ResponseEntity<?> getAllElements(){
        return new ResponseEntity(elementService.getAllElements(), HttpStatus.OK);
    }

    @GetMapping("/element")
    private ResponseEntity<?> getElementById(@RequestParam Long elementId){
        return new ResponseEntity(elementService.getElementById(elementId),HttpStatus.OK);
    }

    @PostMapping("/element")
    private ResponseEntity<?> saveElement(@Valid @RequestBody ElementRequest elementRequest){
        return new ResponseEntity(elementService.saveElement(elementRequest),HttpStatus.OK);
    }

    @PutMapping("/element")
    private ResponseEntity<?> updateElement(@RequestParam Long elementId, @Valid @RequestBody ElementEntity elementEntity){
        return new ResponseEntity(elementService.updateElement(elementId,elementEntity),HttpStatus.OK);
    }

    @DeleteMapping("/element")
    private ResponseEntity<?> deleteElement(@RequestParam Long elementId){
        return new ResponseEntity(elementService.deleteElement(elementId),HttpStatus.OK);
    }
}
