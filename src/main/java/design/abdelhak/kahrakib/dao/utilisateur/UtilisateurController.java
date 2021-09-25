package design.abdelhak.kahrakib.dao.utilisateur;

import design.abdelhak.kahrakib.dto.requests.LoginRequest;
import design.abdelhak.kahrakib.dto.requests.MotDePasseRequest;
import design.abdelhak.kahrakib.dto.responses.LoginResponse;
import design.abdelhak.kahrakib.dto.requests.RegisterRequest;
import design.abdelhak.kahrakib.auth.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authManager;


    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getMotDePasse()));
        }catch (DisabledException e){
            throw new Exception("USER_DISABLED",e);
        }catch (BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS",e);
        }

        //import user details from db
        final UserDetails utilisateurDetails = utilisateurService.loadUserByUsername(loginRequest.getEmail());

        //generate user token
        final String utilisateurToken = jwtUtil.generateJwt(utilisateurDetails);

        return new ResponseEntity(
                new LoginResponse(
                        utilisateurService.getUtilisateurId(jwtUtil.getEmailFromJwt(utilisateurToken)),
                        jwtUtil.getEmailFromJwt(utilisateurToken),
                        utilisateurToken,
                        utilisateurDetails.getAuthorities().iterator().next().toString()),
                HttpStatus.OK);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest){
        return new ResponseEntity(utilisateurService.registerUtilisateur(registerRequest),HttpStatus.OK);
    }

    @PutMapping("/auth/motDePasse")
    public ResponseEntity<?> updateMotDePasse(@Valid @RequestBody MotDePasseRequest motDePasseRequest) throws Exception {
        return new ResponseEntity(utilisateurService.updateMotDePasse(motDePasseRequest),HttpStatus.OK);
    }



    @GetMapping("/api/utilisateurs")
    public ResponseEntity<?> getAllUtilisateurs(){
        return  new ResponseEntity(utilisateurService.getAllUtilisateur(),HttpStatus.OK);
    }

    @GetMapping("/api/utilisateur")
    public ResponseEntity<?> getUtilisateurById(@RequestParam Long utilisateurId){
        return new ResponseEntity(utilisateurService.getUtilisateurById(utilisateurId),HttpStatus.OK);
    }


    @PutMapping("/api/utilisateur")
    public ResponseEntity<?> updateUtilisateur(@RequestParam Long utilisateurId,@Valid @RequestBody UtilisateurEntity utilisateurEntity){
        return new ResponseEntity(utilisateurService.updateUtilisateur(utilisateurId,utilisateurEntity),HttpStatus.OK);
    }

    @DeleteMapping("/api/utilisateur")
    public ResponseEntity<?> deleteUtilisateur(@RequestParam Long utilisateurId){
        return new ResponseEntity(utilisateurService.deleteUtilisateur(utilisateurId),HttpStatus.OK);
    }
}
