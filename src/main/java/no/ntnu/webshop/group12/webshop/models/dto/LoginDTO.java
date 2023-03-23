package no.ntnu.webshop.group12.webshop.models.dto;

public class LoginDTO {

    private final String username;
    private final String password;

    LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

}
