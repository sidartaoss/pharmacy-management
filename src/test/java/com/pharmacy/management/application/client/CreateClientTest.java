package com.pharmacy.management.application.client;

import com.pharmacy.management.application.client.impl.DefaultCreateClient;
import com.pharmacy.management.domain.client.Client;
import com.pharmacy.management.domain.client.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateClientTest {

    private ClientRepository clientRepository;
    private DefaultCreateClient createClient;

    @BeforeEach
    void setUp() {
        clientRepository = mock(ClientRepository.class);
        createClient = new DefaultCreateClient(clientRepository);
    }

    @Test
    void testCreateClientSuccess() {
        // Arrange
        CreateClient.Input input = mock(CreateClient.Input.class);
        when(input.name()).thenReturn("John Doe");
        when(input.email()).thenReturn("john.doe@example.com");
        when(input.cpf()).thenReturn("12345678901");
        when(input.phoneNumber()).thenReturn("1234567890");

        Client client = Client.newClient("1", "John Doe", "john.doe@example.com", "12345678901", "1234567890");
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(clientRepository.nextId()).thenReturn("1");

        // Act
        CreateClient.Output output = createClient.execute(input);

        // Assert
        assertNotNull(output);
        assertEquals("1", output.clientId());

        ArgumentCaptor<Client> clientCaptor = ArgumentCaptor.forClass(Client.class);
        verify(clientRepository).save(clientCaptor.capture());
        Client savedClient = clientCaptor.getValue();
        assertEquals("John Doe", savedClient.name());
        assertEquals("john.doe@example.com", savedClient.email());
        assertEquals("12345678901", savedClient.cpf());
        assertEquals("1234567890", savedClient.phoneNumber());
    }

    @Test
    void testCreateClientWithNullRepository() {
        // Arrange
        ClientRepository nullRepository = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> new DefaultCreateClient(nullRepository));
    }
}