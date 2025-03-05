package com.pharmacy.management.application.client.impl;

import com.pharmacy.management.application.client.CreateClient;
import com.pharmacy.management.domain.client.Client;
import com.pharmacy.management.domain.client.ClientRepository;

import java.util.Objects;

public class DefaultCreateClient extends CreateClient {

    private final ClientRepository clientRepository;

    public DefaultCreateClient(final ClientRepository clientRepository) {
        this.clientRepository = Objects.requireNonNull(clientRepository);
    }

    @Override
    public Output execute(final Input anIn) {
        final var aClient = this.clientRepository.save(
                this.newClientWith(anIn));
        return new StdOutput(aClient.id());
    }

    private Client newClientWith(Input anIn) {
        return Client.newClient(
                this.clientRepository.nextId(),
                anIn.name(),
                anIn.email(),
                anIn.cpf(),
                anIn.phoneNumber()
        );
    }

    record StdOutput(String clientId) implements Output {
    }
}
