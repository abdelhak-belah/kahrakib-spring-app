package design.abdelhak.kahrakib.auth;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JwtUtil {

    /*---------------------------------------------------------------------*/
    private String SECRET_KEY;
    private Integer EXPIRATION_IN_MS;
    /*---------------------------------------------------------------------*/


    @Value("${jwt.secret}")
    public void setSECRET_KEY(String SECRET_KEY) {
        this.SECRET_KEY = SECRET_KEY;
    }

    @Value("${jwt.expirationInMs}")
    public void setEXPIRATION_IN_MS(Integer EXPIRATION_IN_MS) {
        this.EXPIRATION_IN_MS = EXPIRATION_IN_MS;
    }


    public String generateJwt(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();

        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();

        if (roles.contains(new SimpleGrantedAuthority(SecurityKeys.ROLE_ADMIN))){
            claims.put(SecurityKeys.IS_ADMIN_ROLE,true);
        }
        if (roles.contains(new SimpleGrantedAuthority(SecurityKeys.ROLE_CLIENT))){
            claims.put(SecurityKeys.IS_CLIENT_ROLE,true);
        }
        if (roles.contains(new SimpleGrantedAuthority(SecurityKeys.ROLE_CASSIER))){
            claims.put(SecurityKeys.IS_CASSIER_ROLE,true);
        }
        if (roles.contains(new SimpleGrantedAuthority(SecurityKeys.ROLE_CASSIER_RESPO))){
            claims.put(SecurityKeys.IS_CASSIER_RESPO_ROLE,true);
        }
        if (roles.contains(new SimpleGrantedAuthority(SecurityKeys.ROLE_COMPTABLE))){
            claims.put(SecurityKeys.IS_COMPTABLE_ROLE,true);
        }

        return doGenerateJwt(claims,userDetails.getUsername());

    }

    private String doGenerateJwt(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                   .setClaims(claims).setSubject(subject)
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + 1*365*24*60*60*1000))
                   .signWith(SignatureAlgorithm.HS512,SECRET_KEY).compact();
    }


    public Boolean validateJwt(String token){
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e){
            throw new BadCredentialsException("INVALID_CREDENTIALS",e);
        }catch (ExpiredJwtException e){
            throw e;
        }
    }

    public String getEmailFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public List<SimpleGrantedAuthority> getRolesFromJwt(String token){
        List<SimpleGrantedAuthority> roles = null;
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

        Boolean isAdmin = claims.get(SecurityKeys.IS_ADMIN_ROLE,Boolean.class);
        Boolean isClient = claims.get(SecurityKeys.IS_CLIENT_ROLE,Boolean.class);
        Boolean isCassier = claims.get(SecurityKeys.IS_CASSIER_ROLE,Boolean.class);
        Boolean isCassierRespo = claims.get(SecurityKeys.IS_CASSIER_RESPO_ROLE,Boolean.class);
        Boolean isComptable = claims.get(SecurityKeys.IS_COMPTABLE_ROLE,Boolean.class);

        if (isAdmin != null && isAdmin == true){
            roles = Arrays.asList(new SimpleGrantedAuthority(SecurityKeys.ROLE_ADMIN));
        }
        if (isClient != null && isClient == true){
            roles = Arrays.asList(new SimpleGrantedAuthority(SecurityKeys.ROLE_CLIENT));
        }
        if (isCassier != null && isCassier == true){
            roles = Arrays.asList(new SimpleGrantedAuthority(SecurityKeys.ROLE_CASSIER));
        }
        if (isCassierRespo != null && isCassierRespo == true){
            roles = Arrays.asList(new SimpleGrantedAuthority(SecurityKeys.ROLE_CASSIER_RESPO));
        }
        if (isComptable != null && isComptable == true){
            roles = Arrays.asList(new SimpleGrantedAuthority(SecurityKeys.ROLE_COMPTABLE));
        }

        return roles;
    }

}
