package no.ntnu.webshop.group12.webshop.controllers.api;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.time.LocalDate;

import no.ntnu.webshop.group12.webshop.APIBaseTester;
import no.ntnu.webshop.group12.webshop.models.order.purchase.Purchase;

public class PurchaseControllerTest extends APIBaseTester{

    String BASE_URL = "/api/purchases";

    @Test
    @WithMockUser("Test")
    void testGetMyPurchases() throws Exception {
        Purchase[] purchases = objectMapper.readValue(mockMvc.perform(get(BASE_URL+"/me")).andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString(), Purchase[].class);
        assert(purchases.length == 5);
    }

    @Test
    @WithMockUser(username = "Admin", roles = {"ADMIN"})
    void testGetPurchases() throws Exception{
        Purchase[] purchases = objectMapper.readValue(
            mockMvc.perform(get(BASE_URL+"?dateBefore=2021-02-01")).andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString(), Purchase[].class);
        assert(purchases.length == 1);
    }

    @Test
    @WithMockUser(username = "Admin", roles = {"ADMIN"})
    void testGetPurchaseByID() throws Exception {
        Purchase purchase = objectMapper.readValue(mockMvc.perform(get(BASE_URL+"/3"))
                            .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Purchase.class);
        assert(purchase.getId() == 3);
        assert(purchase.getDate().equals(LocalDate.of(2021, 3, 1)));
    }
}
