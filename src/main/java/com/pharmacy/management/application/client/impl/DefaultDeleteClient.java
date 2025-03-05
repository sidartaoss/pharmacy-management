package com.pharmacy.management.application.client.impl;

import com.pharmacy.management.application.client.DeleteClient;
import com.pharmacy.management.domain.client.ClientRepository;

import java.util.Objects;

public class DefaultDeleteClient extends DeleteClient {

    private final ClientRepository clientRepository;

    public DefaultDeleteClient(final ClientRepository clientRepository) {
        this.clientRepository = Objects.requireNonNull(clientRepository);
    }

    @Override
    public void execute(final Input anIn) {
        this.clientRepository.deleteById(anIn.id());
    }
}
