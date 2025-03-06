package com.pharmacy.management.infrastructure.rest;

import com.pharmacy.management.application.medication.*;
import com.pharmacy.management.infrastructure.configuration.json.Json;
import com.pharmacy.management.infrastructure.rest.controllers.MedicationController;
import com.pharmacy.management.infrastructure.rest.models.req.CreateMedicationRequest;
import com.pharmacy.management.infrastructure.rest.models.req.UpdateMedicationRequest;
import com.pharmacy.management.infrastructure.rest.models.res.CreateMedicationResponse;
import com.pharmacy.management.infrastructure.rest.models.res.MedicationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MedicationController.class)
class MedicationRestApiIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateMedication createMedication;

    @MockBean
    private DeleteMedication deleteMedication;

    @MockBean
    private GetMedicationById getMedicationById;

    @MockBean
    private ListMedication listMedication;

    @MockBean
    private UpdateMedication updateMedication;

    @Test
    void testCreateMedication() throws Exception {
        final var request = new CreateMedicationRequest("Medication A", "Brand A", new BigDecimal("10.00"), new BigDecimal("500"));
        final var response = new CreateMedicationResponse("1");
        record StdOutput(String medicationId) implements CreateMedication.Output {
        }
        CreateMedication.Output output = new StdOutput("1");
        when(createMedication.execute(any())).thenReturn(output);

        mockMvc.perform(post("/medications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.medicationId", is(response.medicationId())));
    }

    @Test
    void testGetMedication() throws Exception {
        String medicationId = "1";
        MedicationResponse response = new MedicationResponse("1", "Medication A", "Brand A", new BigDecimal("10.0"), new BigDecimal("500.0"));
        record StdOutput(String id, String name, String brand, BigDecimal price, BigDecimal dosage)
                implements GetMedicationById.Output {
        }
        final GetMedicationById.Output output = new StdOutput("1", "Medication A", "Brand A", new BigDecimal("10.0"), new BigDecimal("500.0"));
        when(getMedicationById.execute(any())).thenReturn(Optional.of(output));

        mockMvc.perform(get("/medications/{id}", medicationId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(medicationId)))
                .andExpect(jsonPath("$.name", is(response.name())))
                .andExpect(jsonPath("$.brand", is(response.brand())))
                .andExpect(jsonPath("$.price", is(response.price().doubleValue())))
                .andExpect(jsonPath("$.dosage", is(response.dosage().doubleValue())));
    }

    @Test
    void testListMedications() throws Exception {
        record StdOutput(String id, String name, String brand, BigDecimal price, BigDecimal dosage)
                implements ListMedication.Output {
        }
        final Set<ListMedication.Output> medications = Set.of(
                new StdOutput("1", "Medication A", "Brand A", new BigDecimal("10.00"), new BigDecimal("500")),
                new StdOutput("2", "Medication B", "Brand B", new BigDecimal("20.00"), new BigDecimal("250"))
        );
        when(listMedication.execute()).thenReturn(medications);

        mockMvc.perform(get("/medications")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testUpdateMedication() throws Exception {
        final var medicationId = "1";
        final var request = new UpdateMedicationRequest("Updated Medication", "Updated Brand", new BigDecimal("15.00"), new BigDecimal("600"));
        record StdOutput(String medicationId) implements UpdateMedication.Output {
        }
        UpdateMedication.Output output = new StdOutput("1");
        when(updateMedication.execute(any())).thenReturn(output);

        mockMvc.perform(put("/medications/{id}", medicationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.writeValueAsString(request)))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.medicationId", is(medicationId)));
    }

    @Test
    void testDeleteMedication() throws Exception {
        String medicationId = "1";

        doNothing().when(deleteMedication).execute(any());

        mockMvc.perform(delete("/medications/{id}", medicationId))
                .andExpect(status().isNoContent());
    }
}