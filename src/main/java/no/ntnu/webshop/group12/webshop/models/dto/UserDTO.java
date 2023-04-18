package no.ntnu.webshop.group12.webshop.models.dto;

import java.util.Set;

import no.ntnu.webshop.group12.webshop.models.Role;
import no.ntnu.webshop.group12.webshop.models.User;

public class UserDTO {

    private int id;

    private String username;

    private Set<Role> roles;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.roles = user.getRoles();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
