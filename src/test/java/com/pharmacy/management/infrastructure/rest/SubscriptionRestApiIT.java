package com.pharmacy.management.infrastructure.rest;

import com.pharmacy.management.application.subscription.ListSubscription;
import com.pharmacy.management.infrastructure.rest.controllers.SubscriptionController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SubscriptionController.class)
class SubscriptionRestApiIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListSubscription listSubscription;

    @Test
    void testListSubscriptions() throws Exception {
        // Arrange
        String clientEmail = "john.doe@example.com";

        record StdOutput(
                String id,
                String clientId,
                String clientName,
                String clientEmail,
                String clientPhoneNumber,
                String medicationId,
                String medicationName,
                String medicationBrand,
                BigDecimal medicationDosage,
                Integer medicationMonthlyRenewalDay) implements ListSubscription.Output {
        }
        final Set<ListSubscription.Output> subscriptions = Set.of(
                new StdOutput("1", "1", "John Doe", "john.doe@example.com", "1234567890", "101", "Medication A", "Brand A", new BigDecimal("500"), 15),
                new StdOutput("2", "1", "John Doe", "john.doe@example.com", "1234567890", "102", "Medication B", "Brand B", new BigDecimal("250"), 20)
        );
        when(listSubscription.execute(any())).thenReturn(subscriptions);

        // Act & Assert
        mockMvc.perform(get("/subscriptions")
                        .param("clientEmail", clientEmail)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].clientId", is("1")))
                .andExpect(jsonPath("$[0].clientName", is("John Doe")))
                .andExpect(jsonPath("$[0].clientEmail", is("john.doe@example.com")))
                .andExpect(jsonPath("$[0].clientPhoneNumber", is("1234567890")))
                .andExpect(jsonPath("$[0].medicationId", is("101")))
                .andExpect(jsonPath("$[0].medicationName", is("Medication A")))
                .andExpect(jsonPath("$[0].medicationBrand", is("Brand A")))
                .andExpect(jsonPath("$[0].medicationDosage", is(500)))
                .andExpect(jsonPath("$[0].medicationMonthlyRenewalDay", is(15)))
                .andExpect(jsonPath("$[1].id", is("2")))
                .andExpect(jsonPath("$[1].clientId", is("1")))
                .andExpect(jsonPath("$[1].clientName", is("John Doe")))
                .andExpect(jsonPath("$[1].clientEmail", is("john.doe@example.com")))
                .andExpect(jsonPath("$[1].clientPhoneNumber", is("1234567890")))
                .andExpect(jsonPath("$[1].medicationId", is("102")))
                .andExpect(jsonPath("$[1].medicationName", is("Medication B")))
                .andExpect(jsonPath("$[1].medicationBrand", is("Brand B")))
                .andExpect(jsonPath("$[1].medicationDosage", is(250)))
                .andExpect(jsonPath("$[1].medicationMonthlyRenewalDay", is(20)));
    }

    @Test
    void testListSubscriptionsNoData() throws Exception {
        // Arrange
        String clientEmail = "john.doe@example.com";
        when(listSubscription.execute(any())).thenReturn(Set.of());

        // Act & Assert
        mockMvc.perform(get("/subscriptions")
                        .param("clientEmail", clientEmail)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }
}