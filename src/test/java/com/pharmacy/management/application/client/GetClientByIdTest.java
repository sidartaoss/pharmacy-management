package com.pharmacy.management.application.client;

import com.pharmacy.management.application.client.impl.DefaultGetClientById;
import com.pharmacy.management.domain.client.Client;
import com.pharmacy.management.domain.client.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetClientByIdTest {

    private ClientRepository clientRepository;
    private DefaultGetClientById getClientById;

    @BeforeEach
    void setUp() {
        clientRepository = mock(ClientRepository.class);
        getClientById = new DefaultGetClientById(clientRepository);
    }

    @Test
    void testGetClientByIdSuccess() {
        // Arrange
        GetClientById.Input input = mock(GetClientById.Input.class);
        when(input.id()).thenReturn("1");

        Client client = Client.newClient("1", "John Doe", "john.doe@example.com", "12345678901", "1234567890");
        when(clientRepository.findById("1")).thenReturn(Optional.of(client));

        // Act
        Optional<GetClientById.Output> output = getClientById.execute(input);

        // Assert
        assertTrue(output.isPresent());
        assertEquals("1", output.get().id());
        assertEquals("John Doe", output.get().name());
        assertEquals("john.doe@example.com", output.get().email());
        assertEquals("12345678901", output.get().cpf());
        assertEquals("1234567890", output.get().phoneNumber());
    }

    @Test
    void testGetClientByIdNotFound() {
        // Arrange
        GetClientById.Input input = mock(GetClientById.Input.class);
        when(input.id()).thenReturn("non-existent-id");

        when(clientRepository.findById("non-existent-id")).thenReturn(Optional.empty());

        // Act
        Optional<GetClientById.Output> output = getClientById.execute(input);

        // Assert
        assertFalse(output.isPresent());
    }

    @Test
    void testGetClientByIdWithNullRepository() {
        // Arrange
        ClientRepository nullRepository = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> new DefaultGetClientById(nullRepository));
    }
}