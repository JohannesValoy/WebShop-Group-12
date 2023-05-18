package no.ntnu.webshop.group12.webshop.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.UnsupportedEncodingException;

import no.ntnu.webshop.group12.webshop.APIBaseTester;
import no.ntnu.webshop.group12.webshop.models.User;

public class UserControllerTest extends APIBaseTester {

    public static final String BASE_URL = "/api/users";

    @Test
    void testCreateAndLogin() throws Exception {
        User user = new User("ThisIsATestAccount", "Test1234");
        String json = objectMapper.writeValueAsString(user);
        User newUser = objectMapper.readValue(mockMvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), User.class);
        assert (newUser.getUsername().equals(user.getUsername()));
        
    }

    @Test
    @WithMockUser(roles = "USER" )
    void testDeleteUserAsWrongAccount() {

    }

    @Test
    @WithMockUser(roles = "User", username = "ThisIsATestAccount")
    void testGetCurrentUser() throws Exception{
        User user = objectMapper.readValue(mockMvc.perform(post(BASE_URL+"/me"))
        .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), User.class);
        assert (user.getUsername().equals("ThisIsATestAccount"));
    }

    @Test
    void testGetUser() {

    }

    @Test
    void testGetUserCount() {

    }

    @Test
    void testGetUsersByFilter() {

    }

    @Test
    void testingAccessibility() {

    }
}
