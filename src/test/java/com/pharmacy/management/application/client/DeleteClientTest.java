package com.pharmacy.management.application.client;

import com.pharmacy.management.application.client.impl.DefaultDeleteClient;
import com.pharmacy.management.domain.client.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteClientTest {

    private ClientRepository clientRepository;
    private DefaultDeleteClient deleteClient;

    @BeforeEach
    void setUp() {
        clientRepository = mock(ClientRepository.class);
        deleteClient = new DefaultDeleteClient(clientRepository);
    }

    @Test
    void testDeleteClientSuccess() {
        // Arrange
        DeleteClient.Input input = mock(DeleteClient.Input.class);
        when(input.id()).thenReturn("1");

        doNothing().when(clientRepository).deleteById("1");

        // Act
        deleteClient.execute(input);

        // Assert
        verify(clientRepository, times(1)).deleteById("1");
    }

    @Test
    void testDeleteClientWithNullRepository() {
        // Arrange
        ClientRepository nullRepository = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> new DefaultDeleteClient(nullRepository));
    }

    @Test
    void testDeleteClientWithNonExistentId() {
        // Arrange
        DeleteClient.Input input = mock(DeleteClient.Input.class);
        when(input.id()).thenReturn("non-existent-id");

        doNothing().when(clientRepository).deleteById("non-existent-id");

        // Act & Assert
        assertDoesNotThrow(() -> deleteClient.execute(input));
    }
}