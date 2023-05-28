package no.ntnu.webshop.group12.webshop.controllers.api;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import no.ntnu.webshop.group12.webshop.APIBaseTester;
import no.ntnu.webshop.group12.webshop.models.User;
import no.ntnu.webshop.group12.webshop.models.dto.LoginDTO;

public class UserControllerTest extends APIBaseTester {


    public static final String BASE_URL = "/api/users";

    @Test
    @WithMockUser("ThisIsATestAccount")
    void testCreateAndDelete() throws Exception {
        LoginDTO loginDTO = new LoginDTO("ThisIsATestAccount", "Test1234");
        String json = objectMapper.writeValueAsString(loginDTO);
        User newUser = objectMapper.readValue(mockMvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), User.class);
        assert (newUser.getUsername().equals(loginDTO.getUsername()));
        mockMvc.perform(delete(BASE_URL+"/me"))
        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testDeleteUserAsWrongAccount() throws Exception {
        mockMvc.perform(delete(BASE_URL+"/1")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser("Test")
    void testGetCurrentUser() throws Exception{
       User user = objectMapper.readValue(mockMvc.perform(get(BASE_URL+"/me")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), User.class);
       assertEquals("Test", user.getUsername());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetUser() throws Exception {
        User user = objectMapper.readValue(mockMvc.perform(get(BASE_URL+"/1")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), User.class);
        assertEquals("Admin", user.getUsername());
        mockMvc.perform(get(BASE_URL+"/4")).andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetUserCount() throws Exception {
        mockMvc.perform(get(BASE_URL+"/count")).andExpect(status().isOk()).andExpect(content().string("2"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetUsersByFilter() throws Exception {
        User[] users = objectMapper.readValue(mockMvc.perform(get(BASE_URL+"?username=Test")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), User[].class);
        assertEquals(1, users.length);
    }

    @Test
    @WithMockUser(roles = "USER")
    void testingAccessibility() throws Exception {
        mockMvc.perform(get(BASE_URL+"/1")).andExpect(status().isForbidden());
        mockMvc.perform(get(BASE_URL+"/count")).andExpect(status().isForbidden());
        mockMvc.perform(get(BASE_URL+"?username=Test")).andExpect(status().isForbidden());
    }
}
