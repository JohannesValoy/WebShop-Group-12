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

import java.util.List;

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
    void testCreateAndDeleteCategory() throws JsonProcessingException, Exception {
        Category category = new Category("test");
        String json = objectMapper.writeValueAsString(category);

        // Create category
        Category returnCategory = objectMapper
                .readValue(mockMvc.perform(post("/api/category").contentType(MediaType.APPLICATION_JSON).content(json))
                        .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Category.class);

        // Fetch to ensure created
        Category fetchedCategory = objectMapper.readValue(
                mockMvc.perform(get("/api/category/" + returnCategory.getId())).andExpect(status().isOk()).andReturn()
                        .getResponse().getContentAsString(),
                Category.class);

        assert (returnCategory.getName().equals(category.getName()));
        assert (returnCategory.equals(fetchedCategory));

        // Delete category
        mockMvc.perform(delete("/api/category/" + returnCategory.getId())).andExpect(status().isOk());

        // Fetch to ensure deleted
        mockMvc.perform(get("/api/category/" + returnCategory.getId())).andExpect(status().isNotFound());

    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetCategory() throws Exception {
        mockMvc.perform(get("/api/category/1")).andExpect(status().isOk());
        mockMvc.perform(get("/api/category/1000")).andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetCategoryByFilter() throws Exception {
        List categories = objectMapper.readValue(mockMvc.perform(get("/api/category/filter?name=Gaming"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), List.class);

        mockMvc.perform(get("/api/category/name/1000")).andExpect(status().isNotFound());
    }

    @Test
    void testGetCategoryCount() {

    }
}
