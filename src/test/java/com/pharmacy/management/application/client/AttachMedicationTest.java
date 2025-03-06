package com.pharmacy.management.application.client;

import com.pharmacy.management.application.client.impl.DefaultAttachMedication;
import com.pharmacy.management.domain.client.Client;
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
    private DefaultAttachMedication attachMedication;

    @BeforeEach
    void setUp() {
        clientRepository = mock(ClientRepository.class);
        medicationRepository = mock(MedicationRepository.class);
        attachMedication = new DefaultAttachMedication(medicationRepository, clientRepository);
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

        when(clientRepository.findById("1")).thenReturn(Optional.of(client));
        when(medicationRepository.findById("101")).thenReturn(Optional.of(medication));

        // Act
        attachMedication.execute(input);

        // Assert
        ArgumentCaptor<Client> clientCaptor = ArgumentCaptor.forClass(Client.class);
        verify(clientRepository).save(clientCaptor.capture());
        Client updatedClient = clientCaptor.getValue();
        assertEquals(client.id(), updatedClient.allMedications().iterator().next().clientId());
        assertEquals(medication.id(), updatedClient.allMedications().iterator().next().medicationId());
        assertEquals(expectedMonthlyRenewalDay, updatedClient.allMedications().iterator().next().monthlyRenewalDay());
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
        MedicationRepository nullMedicationRepository = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> new DefaultAttachMedication(nullMedicationRepository, nullClientRepository));
    }
}