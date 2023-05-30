package no.ntnu.webshop.group12.webshop.controllers.api;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import no.ntnu.webshop.group12.webshop.APIBaseTester;
import no.ntnu.webshop.group12.webshop.models.order.cart.Cart;

public class CartControllerTest extends APIBaseTester{

    String BASE_URL = "/api/carts";

    @Test
    @WithMockUser(username = "Test", roles = "USER")
    void testAddChangeAndDeleteProductFromCart() throws Exception {
        mockMvc.perform(post(BASE_URL+"/me/product/1")).andExpect(status().isOk());
        Cart cart = objectMapper.readValue(mockMvc.perform(get(BASE_URL+"/me")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Cart.class);
        assertEquals(1, cart.getItems().size());
        mockMvc.perform(patch(BASE_URL+"/me/product/1/quantity/2")).andExpect(status().isOk());
        cart = objectMapper.readValue(mockMvc.perform(get(BASE_URL+"/me")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Cart.class);
        assertEquals(2, cart.getItems().iterator().next().getAmount());
        mockMvc.perform(post(BASE_URL+"/me/product/3")).andExpect(status().isOk());
        cart = objectMapper.readValue(mockMvc.perform(get(BASE_URL+"/me")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Cart.class);
        assertEquals(2, cart.getItems().size());
        mockMvc.perform(delete(BASE_URL+"/me/product/1")).andExpect(status().isOk());
        cart = objectMapper.readValue(mockMvc.perform(get(BASE_URL+"/me")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Cart.class);
        assertEquals(1, cart.getItems().size());
        mockMvc.perform(post(BASE_URL+"/me/product/1")).andExpect(status().isOk());
        cart = objectMapper.readValue(mockMvc.perform(delete(BASE_URL+"/me")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Cart.class);
        assertEquals(0, cart.getItems().size());
    }

    @Test
    @WithMockUser("Test")
    void testGetMyCart() throws Exception {
        Cart cart = objectMapper.readValue(mockMvc.perform(get(BASE_URL+"/me")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Cart.class);
        assertEquals(0, cart.getItems().size());
    }

    @Test
    @WithMockUser(username = "Admin", roles = "ADMIN")
    void testGetCartsByFilterAndOneCart() throws Exception {
        Cart[] carts = objectMapper.readValue(mockMvc.perform(get(BASE_URL+"?user.username=Test")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Cart[].class);
        assertEquals(1, carts.length);
        Cart fetchedCart = objectMapper.readValue(mockMvc.perform(get(BASE_URL+"/"+carts[0].getId())).andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Cart.class);
        assertEquals(carts[0], fetchedCart);
    }
}