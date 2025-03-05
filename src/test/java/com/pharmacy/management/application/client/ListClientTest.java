package com.pharmacy.management.application.client;

import com.pharmacy.management.application.client.impl.DefaultListClient;
import com.pharmacy.management.domain.client.Client;
import com.pharmacy.management.domain.client.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListClientTest {

    private ClientRepository clientRepository;
    private DefaultListClient listClient;

    @BeforeEach
    void setUp() {
        clientRepository = mock(ClientRepository.class);
        listClient = new DefaultListClient(clientRepository);
    }

    @Test
    void testListClientsSuccess() {
        // Arrange
        Client client1 = Client.newClient("1", "John Doe", "john.doe@example.com", "12345678901", "1234567890");
        Client client2 = Client.newClient("2", "Jane Doe", "jane.doe@example.com", "09876543210", "0987654321");
        Set<Client> clients = Set.of(client1, client2);
        when(clientRepository.findAll()).thenReturn(clients);

        // Act
        Set<ListClient.Output> output = listClient.execute();

        // Assert
        assertNotNull(output);
        assertEquals(2, output.size());
        output.stream().findFirst().ifPresent(actual -> {
            assertEquals("1", actual.id());
            assertEquals("John Doe", actual.name());
        });
        output.stream().skip(1).findFirst().ifPresent(actual -> {
            assertEquals("2", actual.id());
            assertEquals("Jane Doe", actual.name());
        });
    }

    @Test
    void testListClientsEmpty() {
        // Arrange
        when(clientRepository.findAll()).thenReturn(Set.of());

        // Act
        final var output = listClient.execute();

        // Assert
        assertNotNull(output);
        assertTrue(output.isEmpty());
    }

    @Test
    void testListClientsWithNullRepository() {
        // Arrange
        ClientRepository nullRepository = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> new DefaultListClient(nullRepository));
    }
}