package com.pharmacy.management.application.subscription;

import com.pharmacy.management.application.subscription.impl.DefaultCreateSubscription;
import com.pharmacy.management.domain.client.Client;
import com.pharmacy.management.domain.client.ClientRepository;
import com.pharmacy.management.domain.exceptions.NotFoundException;
import com.pharmacy.management.domain.medication.Medication;
import com.pharmacy.management.domain.medication.MedicationRepository;
import com.pharmacy.management.domain.subscription.Subscription;
import com.pharmacy.management.domain.subscription.SubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateSubscriptionTest {

    private ClientRepository clientRepository;
    private MedicationRepository medicationRepository;
    private SubscriptionRepository subscriptionRepository;
    private DefaultCreateSubscription createSubscription;

    @BeforeEach
    void setUp() {
        clientRepository = mock(ClientRepository.class);
        medicationRepository = mock(MedicationRepository.class);
        subscriptionRepository = mock(SubscriptionRepository.class);
        createSubscription = new DefaultCreateSubscription(clientRepository, medicationRepository, subscriptionRepository);
    }

    @Test
    void testCreateSubscriptionSuccess() {
        // Arrange
        CreateSubscription.Input input = mock(CreateSubscription.Input.class);
        when(input.clientId()).thenReturn("1");
        when(input.medicationId()).thenReturn("101");

        final var client = Client.newClient("1", "John Doe", "john.doe@example.com", "27227066100", "1234567890");
        final var medication = Medication.newMedication("101", "Medication A", "Brand A", new BigDecimal("10.00"), new BigDecimal("500"));
        final var subscription = Subscription.newSubscription("1", "1", "John Doe", "john.doe@example.com", "1234567890", "101", "Medication A", "Brand A", new BigDecimal("500"), 7);

        when(clientRepository.findById("1")).thenReturn(Optional.of(client));
        when(medicationRepository.findById("101")).thenReturn(Optional.of(medication));
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);

        // Act
        CreateSubscription.Output output = createSubscription.execute(input);

        // Assert
        assertNotNull(output);
        assertEquals("1", output.subscriptionId());
        verify(subscriptionRepository, times(1)).save(any(Subscription.class));
    }

    @Test
    void testCreateSubscriptionClientNotFound() {
        // Arrange
        CreateSubscription.Input input = mock(CreateSubscription.Input.class);
        when(input.clientId()).thenReturn("1");
        when(input.medicationId()).thenReturn("101");

        when(clientRepository.findById("1")).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> createSubscription.execute(input));
        assertEquals("Client with ID 1 not found", exception.getMessage());
    }

    @Test
    void testCreateSubscriptionMedicationNotFound() {
        // Arrange
        CreateSubscription.Input input = mock(CreateSubscription.Input.class);
        when(input.clientId()).thenReturn("1");
        when(input.medicationId()).thenReturn("101");

        Client client = Client.newClient("1", "John Doe", "john.doe@example.com", "27227066100", "1234567890");

        when(clientRepository.findById("1")).thenReturn(Optional.of(client));
        when(medicationRepository.findById("101")).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> createSubscription.execute(input));
        assertEquals("Medication with ID 101 not found", exception.getMessage());
    }
}