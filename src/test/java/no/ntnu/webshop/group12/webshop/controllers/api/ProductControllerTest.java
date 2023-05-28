package no.ntnu.webshop.group12.webshop.controllers.api;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.core.JsonProcessingException;

import no.ntnu.webshop.group12.webshop.APIBaseTester;
import no.ntnu.webshop.group12.webshop.models.product.Product;


public class ProductControllerTest extends APIBaseTester {

        private static final String BASE_URL = "/api/products";

        @Test
        @WithMockUser(roles = "ADMIN")
        void testCreateAndDeleteproduct() throws JsonProcessingException, Exception {
                Product product = new Product("test", "A test", 100, 10);
                String json = objectMapper.writeValueAsString(product);

                // Create product
                Product returnProduct = objectMapper
                                .readValue(mockMvc
                                                .perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON)
                                                                .content(json))
                                                .andExpect(status().isOk()).andReturn().getResponse()
                                                .getContentAsString(), Product.class);

                // Fetch to ensure created
                Product fetchedproduct = objectMapper.readValue(
                                mockMvc.perform(get(BASE_URL + "/" + returnProduct.getId())).andExpect(status().isOk())
                                                .andReturn()
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
                Product[] products = objectMapper.readValue(mockMvc.perform(get(BASE_URL + "?name=Gaming"))
                                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Product[].class);
                assertEquals(5, products.length);
                products = objectMapper.readValue(mockMvc.perform(get(BASE_URL + "?name=Gaming&page=1"))
                                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Product[].class);
                assertEquals(1, products.length);
        }

        @Test
        void testGetproductCount()
                        throws Exception {
                Integer categoriesCount = objectMapper.readValue(mockMvc.perform(get(BASE_URL + "/" + "count"))
                                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(),
                                Integer.class);
                assertEquals(6, categoriesCount);
        }

        @Test
        @WithMockUser(roles = "USER")
        void testNotAccess() throws Exception {
                Product product = new Product("test", null, 0, 0);
                String json = objectMapper.writeValueAsString(product);
                mockMvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isForbidden());
                mockMvc.perform(delete(BASE_URL + "/" + "1")).andExpect(status().isForbidden());
        }
}
