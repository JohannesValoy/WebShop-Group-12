package no.ntnu.webshop.group12.webshop.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.ntnu.webshop.group12.webshop.models.product.Product;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private static final String BASE_URL = "/api/product";

    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreateAndDeleteproduct() throws JsonProcessingException, Exception {
        Product product = new Product("test", "A test", 100, 10);
        String json = objectMapper.writeValueAsString(product);

        // Create product
        Product returnProduct = objectMapper
                .readValue(mockMvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON).content(json))
                        .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Product.class);

        // Fetch to ensure created
        Product fetchedproduct = objectMapper.readValue(
                mockMvc.perform(get(BASE_URL + "/" + returnProduct.getId())).andExpect(status().isOk()).andReturn()
                        .getResponse().getContentAsString(),
                Product.class);

        assert (returnProduct.getName().equals(product.getName()));
        assert (returnProduct.equals(fetchedproduct));

        // Delete product
        mockMvc.perform(delete(BASE_URL + "/" + returnProduct.getId())).andExpect(status().isOk());

        // Fetch to ensure deleted
        mockMvc.perform(get(BASE_URL + "/" + returnProduct.getId())).andExpect(status().isNotFound());
    }

    @Test
    void testGetproduct() throws Exception {
        mockMvc.perform(get(BASE_URL + "/" + "1")).andExpect(status().isOk());
        mockMvc.perform(get(BASE_URL + "/" + "1000")).andExpect(status().isNotFound());
    }

    @Test
    void testGetproductByFilter() throws Exception {
        List products = objectMapper.readValue(mockMvc.perform(get(BASE_URL + "/filter?name=Gaming"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), List.class);
        assertEquals(3, products.size());
        products = objectMapper
                .readValue(mockMvc.perform(get(BASE_URL + "/filter?category.name=Gaming")).andReturn().getResponse()
                        .getContentAsString(), List.class);
    }

    @Test
    void testGetproductCount()
            throws Exception {
        Integer categoriesCount = objectMapper.readValue(mockMvc.perform(get(BASE_URL + "/" + "count"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Integer.class);
        assertEquals(3, categoriesCount);
    }

    @Test
    @WithMockUser(roles = "USER")
    void testNotAccess() throws Exception {
        Product product = new Product("test", null, 0, 0);
        String json = objectMapper.writeValueAsString(product);
        mockMvc.perform(post(BASE_URL).content(json)).andExpect(status().isForbidden());
        mockMvc.perform(delete(BASE_URL + "/" + "1")).andExpect(status().isForbidden());
    }
}