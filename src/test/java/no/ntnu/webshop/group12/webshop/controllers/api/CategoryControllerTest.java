package no.ntnu.webshop.group12.webshop.controllers.api;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import no.ntnu.webshop.group12.webshop.APIBaseTester;
import no.ntnu.webshop.group12.webshop.models.product.Category;

public class CategoryControllerTest extends APIBaseTester{

    private static final String BASE_URL = "/api/categories";

    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreateAndDeleteCategory() throws Exception {
        Category category = new Category("test");
        String json = objectMapper.writeValueAsString(category);

        // Create category
        Category returnCategory = objectMapper
                .readValue(mockMvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON).content(json))
                        .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Category.class);

        // Fetch to ensure created
        Category fetchedCategory = objectMapper.readValue(
                mockMvc.perform(get(BASE_URL + "/" + returnCategory.getId())).andExpect(status().isOk()).andReturn()
                        .getResponse().getContentAsString(),
                Category.class);

        assert (returnCategory.getName().equals(category.getName()));
        assert (returnCategory.equals(fetchedCategory));

        // Delete category
        mockMvc.perform(delete(BASE_URL + "/" + returnCategory.getId())).andExpect(status().isOk());

        // Fetch to ensure deleted
        mockMvc.perform(get(BASE_URL + "/" + returnCategory.getId())).andExpect(status().isNotFound());
    }

    @Test
    void testGetCategory() throws Exception {
        mockMvc.perform(get(BASE_URL + "/" + "1")).andExpect(status().isOk());
        mockMvc.perform(get(BASE_URL + "/" + "1000")).andExpect(status().isNotFound());
    }

    @Test
    void testGetCategoryByFilter() throws Exception {
        Category[] categories = objectMapper.readValue(mockMvc.perform(get(BASE_URL + "?name=Gaming"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Category[].class);
        assertEquals(1, categories.length);
    }

    @Test
    void testGetCategoryCount()
            throws Exception {
        Integer categoriesCount = objectMapper.readValue(mockMvc.perform(get(BASE_URL + "/" + "count"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Integer.class);
        assertEquals(5, categoriesCount);
    }

    @Test
    @WithMockUser(roles = "USER")
    void testNotAccess() throws Exception {
        Category category = new Category("test");
        String json = objectMapper.writeValueAsString(category);
        mockMvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isForbidden());
        mockMvc.perform(delete(BASE_URL + "/" + "1")).andExpect(status().isForbidden());
    }
}
