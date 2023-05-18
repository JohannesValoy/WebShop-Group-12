package no.ntnu.webshop.group12.webshop.models.dto;

import lombok.Getter;

@Getter
public class LoginDTO {

    private final String username;
    private final String password;

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginDTO() {
        this.username = null;
        this.password = null;
    }
}
