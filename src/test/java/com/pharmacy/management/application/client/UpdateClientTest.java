package com.pharmacy.management.application.client;

import com.pharmacy.management.application.client.impl.DefaultUpdateClient;
import com.pharmacy.management.domain.client.Client;
import com.pharmacy.management.domain.client.ClientRepository;
import com.pharmacy.management.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateClientTest {

    private ClientRepository clientRepository;
    private DefaultUpdateClient updateClient;

    @BeforeEach
    void setUp() {
        clientRepository = mock(ClientRepository.class);
        updateClient = new DefaultUpdateClient(clientRepository);
    }

    @Test
    void testUpdateClientSuccess() {
        // Arrange
        UpdateClient.Input input = mock(UpdateClient.Input.class);
        when(input.id()).thenReturn("1");
        when(input.name()).thenReturn("John Doe Updated");
        when(input.email()).thenReturn("john.doe.updated@example.com");
        when(input.cpf()).thenReturn("12345678901");
        when(input.phoneNumber()).thenReturn("1234567890");

        Client existingClient = Client.newClient("1", "John Doe", "john.doe@example.com", "12345678901", "1234567890");
        when(clientRepository.findById("1")).thenReturn(Optional.of(existingClient));

        // Act
        UpdateClient.Output output = updateClient.execute(input);

        // Assert
        assertNotNull(output);
        assertEquals("1", output.clientId());

        ArgumentCaptor<Client> clientCaptor = ArgumentCaptor.forClass(Client.class);
        verify(clientRepository).save(clientCaptor.capture());
        Client updatedClient = clientCaptor.getValue();
        assertEquals("John Doe Updated", updatedClient.name());
        assertEquals("john.doe.updated@example.com", updatedClient.email());
        assertEquals("12345678901", updatedClient.cpf());
        assertEquals("1234567890", updatedClient.phoneNumber());
    }

    @Test
    void testUpdateClientNotFound() {
        // Arrange
        UpdateClient.Input input = mock(UpdateClient.Input.class);
        when(input.id()).thenReturn("non-existent-id");

        when(clientRepository.findById("non-existent-id")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> updateClient.execute(input));
    }

    @Test
    void testUpdateClientWithNullRepository() {
        // Arrange
        ClientRepository nullRepository = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> new DefaultUpdateClient(nullRepository));
    }
}