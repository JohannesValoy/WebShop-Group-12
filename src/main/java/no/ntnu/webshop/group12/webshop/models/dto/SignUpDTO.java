package no.ntnu.webshop.group12.webshop.models.dto;

public class SignUpDTO {

    private final String signUpUsername;
    private final String signUpPassword;
    private final String confirmSignUpPassword;

    SignUpDTO(String signUpUsername, String signUpPassword, String confirmSignUpPassword) {
        this.signUpUsername = signUpUsername;
        this.signUpPassword = signUpPassword;
        this.confirmSignUpPassword = confirmSignUpPassword;
    }

    public String getSignUpUsername() {
        return signUpUsername;
    }

    public String getSignUpPassword() {
        return signUpPassword;
    }

    public String getConfirmSignUpPassword() {
        return confirmSignUpPassword;
    }

}
