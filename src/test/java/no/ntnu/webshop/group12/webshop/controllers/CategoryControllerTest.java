package no.ntnu.webshop.group12.webshop.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.ntnu.webshop.group12.webshop.models.product.Category;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreateCategory() throws JsonProcessingException, Exception {
        Category category = new Category("test");
        String json = objectMapper.writeValueAsString(category);
        mockMvc.perform(post("/api/category").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteCategory() {

    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetAllCategories() {

    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetCategory() throws Exception {
        mockMvc.perform(get("/api/category/1")).andExpect(status().isOk());
        mockMvc.perform(get("/api/category/1000")).andExpect(status().isNotFound());
    }

    @Test
    void testGetCategoryCount() {

    }
}
