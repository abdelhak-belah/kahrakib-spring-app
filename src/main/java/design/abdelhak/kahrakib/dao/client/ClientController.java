package design.abdelhak.kahrakib.dao.client;

import design.abdelhak.kahrakib.dto.requests.ClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    private ResponseEntity<?> getAllClients(){
        return new ResponseEntity(clientService.getAllClients(), HttpStatus.OK);
    }

    @GetMapping("/client")
    private ResponseEntity<?> getClientById(@RequestParam Long clientId){
        return new ResponseEntity(clientService.getClientById(clientId),HttpStatus.OK);
    }

    @PostMapping("/client")
    private ResponseEntity<?> saveClient(@Valid @RequestBody ClientRequest clientRequest){
        return new ResponseEntity(clientService.saveClient(clientRequest),HttpStatus.OK);
    }

    @PutMapping("/client")
    private ResponseEntity<?> updateClient(@RequestParam Long clientId, @Valid @RequestBody ClientEntity clientEntity){
        return new ResponseEntity(clientService.updateClient(clientId,clientEntity),HttpStatus.OK);
    }

    @DeleteMapping("/client")
    private ResponseEntity<?> deleteClient(@RequestParam Long clientId){
        return new ResponseEntity(clientService.deleteClient(clientId),HttpStatus.OK);
    }
}
