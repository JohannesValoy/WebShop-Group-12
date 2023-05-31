package no.ntnu.webshop.group12.webshop.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "SignupDTO",description = "A login request")
public class LoginDTO {

    private String username;
    private String password;

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginDTO() {
        // Empty constructor for Spring
    }
}
