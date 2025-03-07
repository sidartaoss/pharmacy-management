package com.pharmacy.management.application.client;

import com.pharmacy.management.application.client.impl.DefaultAttachMedication;
import com.pharmacy.management.domain.client.Client;
import com.pharmacy.management.domain.client.ClientMedication;
import com.pharmacy.management.domain.client.ClientMedicationRepository;
import com.pharmacy.management.domain.client.ClientRepository;
import com.pharmacy.management.domain.exceptions.NotFoundException;
import com.pharmacy.management.domain.medication.Medication;
import com.pharmacy.management.domain.medication.MedicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AttachMedicationTest {

    private ClientRepository clientRepository;
    private MedicationRepository medicationRepository;
    private ClientMedicationRepository clientMedicationRepository;
    private DefaultAttachMedication attachMedication;

    @BeforeEach
    void setUp() {
        clientRepository = mock(ClientRepository.class);
        medicationRepository = mock(MedicationRepository.class);
        clientMedicationRepository = mock(ClientMedicationRepository.class);
        attachMedication = new DefaultAttachMedication(clientRepository, clientMedicationRepository);
    }

    @Test
    void testAttachMedicationSuccess() {
        // Arrange
        final var expectedMonthlyRenewalDay = 15;
        AttachMedication.Input input = mock(AttachMedication.Input.class);
        when(input.clientId()).thenReturn("1");
        when(input.medicationId()).thenReturn("101");
        when(input.monthlyRenewalDay()).thenReturn(expectedMonthlyRenewalDay);

        Client client = Client.newClient("1", "John Doe", "john.doe@example.com", "12345678901", "1234567890");
        Medication medication = Medication.newMedication("101", "Medication A", "Description A", BigDecimal.valueOf(9.99), BigDecimal.valueOf(100));
        ClientMedication clientMedication = ClientMedication.newClientMedication("1", client.id(), medication.id(), expectedMonthlyRenewalDay);

        when(clientRepository.findById("1")).thenReturn(Optional.of(client));
        when(medicationRepository.findById("101")).thenReturn(Optional.of(medication));
        when(clientMedicationRepository.findByClientIdAndMedicationId("1", "101")).thenReturn(Optional.of(clientMedication));

        // Act
        attachMedication.execute(input);

        // Assert
        ArgumentCaptor<Client> clientCaptor = ArgumentCaptor.forClass(Client.class);
        verify(clientRepository).save(clientCaptor.capture());
        final var savedClient = clientCaptor.getValue();
        assertEquals("1", savedClient.id());
        assertEquals("101", savedClient.allMedications().stream().findFirst().orElseThrow().medicationId());
        assertEquals(expectedMonthlyRenewalDay, savedClient.allMedications().stream().findFirst().orElseThrow()
                .monthlyRenewalDay());
    }

    @Test
    void testAttachMedicationClientNotFound() {
        // Arrange
        AttachMedication.Input input = mock(AttachMedication.Input.class);
        when(input.clientId()).thenReturn("non-existent-client-id");

        when(clientRepository.findById("non-existent-client-id")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> attachMedication.execute(input));
    }

    @Test
    void testAttachMedicationMedicationNotFound() {
        // Arrange
        AttachMedication.Input input = mock(AttachMedication.Input.class);
        when(input.clientId()).thenReturn("1");
        when(input.medicationId()).thenReturn("non-existent-medication-id");

        Client client = Client.newClient("1", "John Doe", "john.doe@example.com", "12345678901", "1234567890");
        when(clientRepository.findById("1")).thenReturn(Optional.of(client));
        when(medicationRepository.findById("non-existent-medication-id")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> attachMedication.execute(input));
    }

    @Test
    void testAttachMedicationWithNullRepositories() {
        // Arrange
        ClientRepository nullClientRepository = null;
        ClientMedicationRepository nullClientMedicationRepository = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> new DefaultAttachMedication(nullClientRepository, nullClientMedicationRepository));
    }
}