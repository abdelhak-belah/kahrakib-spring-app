package design.abdelhak.kahrakib.dto.responses;

public class LoginResponse {

    private Long id;
    private String email;
    private String jwt;
    private String role;

    public LoginResponse() {
    }

    public LoginResponse(Long id, String email, String jwt, String role) {
        this.id = id;
        this.email = email;
        this.jwt = jwt;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
