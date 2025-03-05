package com.pharmacy.management.application.client.impl;

import com.pharmacy.management.application.client.UpdateClient;
import com.pharmacy.management.domain.client.Client;
import com.pharmacy.management.domain.client.ClientRepository;
import com.pharmacy.management.domain.exceptions.NotFoundException;

import java.util.Objects;

public class DefaultUpdateClient extends UpdateClient {

    private final ClientRepository clientRepository;

    public DefaultUpdateClient(final ClientRepository clientRepository) {
        this.clientRepository = Objects.requireNonNull(clientRepository);
    }

    @Override
    public Output execute(final Input anIn) {
        final var aClient = this.clientRepository.findById(anIn.id())
                .orElseThrow(() -> NotFoundException.with("Client with id %s could not be found".formatted(anIn.id())));
        this.clientRepository.save(
                this.newClientWith(anIn));
        return new StdOutput(aClient.id());
    }

    private Client newClientWith(UpdateClient.Input anIn) {
        return Client.newClient(
                anIn.id(),
                anIn.name(),
                anIn.email(),
                anIn.cpf(),
                anIn.phoneNumber()
        );
    }

    record StdOutput(String clientId) implements UpdateClient.Output {
    }
}
