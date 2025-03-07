package com.pharmacy.management.infrastructure.rest;

import com.pharmacy.management.application.client.*;
import com.pharmacy.management.infrastructure.configuration.json.Json;
import com.pharmacy.management.infrastructure.mediator.AttachMediator;
import com.pharmacy.management.infrastructure.rest.controllers.ClientController;
import com.pharmacy.management.infrastructure.rest.models.req.CreateClientRequest;
import com.pharmacy.management.infrastructure.rest.models.req.SubscribeMedicationRequest;
import com.pharmacy.management.infrastructure.rest.models.req.UpdateClientRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
class ClientRestApiIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateClient createClient;

    @MockBean
    private GetClientById getClientById;

    @MockBean
    private ListClient listClient;

    @MockBean
    private UpdateClient updateClient;

    @MockBean
    private DeleteClient deleteClient;

    @MockBean
    private AttachMedication attachMedication;

    @MockBean
    private AttachMediator attachMediator;

    @Test
    void testCreateClient() throws Exception {
        CreateClientRequest request = new CreateClientRequest("John Doe", "john.doe@example.com", "12345678901", "1234567890");
        record StdOutput(String clientId) implements CreateClient.Output {
        }
        CreateClient.Output output = new StdOutput("1");
        when(createClient.execute(any())).thenReturn(output);

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/clients/1"))
                .andExpect(jsonPath("$.clientId").value("1"));
    }

    @Test
    void testGetClientById() throws Exception {
        record StdOutput(String id, String name, String email, String cpf,
                         String phoneNumber) implements GetClientById.Output {
        }
        GetClientById.Output output = new StdOutput("1", "John Doe", "john.doe@example.com", "12345678901", "1234567890");
        when(getClientById.execute(any())).thenReturn(Optional.of(output));

        mockMvc.perform(get("/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testGetClientByIdNotFound() throws Exception {
        when(getClientById.execute(any())).thenReturn(Optional.empty());

        mockMvc.perform(get("/clients/non-existent-id"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testListClients() throws Exception {
        record StdOutput(String id, String name, String email, String cpf, String phoneNumber)
                implements ListClient.Output {
        }
        final Set<ListClient.Output> clients = Set.of(
                new StdOutput("1", "John Doe", "john.doe@example.com", "12345678901", "1234567890"),
                new StdOutput("2", "Jane Doe", "jane.doe@example.com", "09876543210", "0987654321")
        );
        when(listClient.execute()).thenReturn(clients);

        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testUpdateClient() throws Exception {
        UpdateClientRequest request = new UpdateClientRequest("John Doe Updated", "john.doe.updated@example.com", "12345678901", "1234567890");
        record StdOutput(String clientId) implements UpdateClient.Output {
        }
        UpdateClient.Output output = new StdOutput("1");
        when(updateClient.execute(any())).thenReturn(output);

        mockMvc.perform(put("/clients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.writeValueAsString(request)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.clientId").value("1"));
    }

    @Test
    void testDeleteClient() throws Exception {
        mockMvc.perform(delete("/clients/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testSubscribeMedication() throws Exception {
        SubscribeMedicationRequest request = new SubscribeMedicationRequest("101", 15);

        mockMvc.perform(post("/clients/1/subscribe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.writeValueAsString(request)))
                .andExpect(status().isNoContent());
    }
}