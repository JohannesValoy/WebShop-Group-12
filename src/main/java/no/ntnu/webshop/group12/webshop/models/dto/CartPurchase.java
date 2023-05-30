package no.ntnu.webshop.group12.webshop.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "A purchase made from the cart")
public class CartPurchase {
    String firstName;
    String surname;
    String address;
    String phoneNumber;
    String cardNumber;
    String cvc;

    public CartPurchase() {
        // Empty constructor for Spring
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvc() {
        return cvc;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

}
