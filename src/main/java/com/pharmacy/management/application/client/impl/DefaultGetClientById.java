package com.pharmacy.management.application.client.impl;

import com.pharmacy.management.application.client.GetClientById;
import com.pharmacy.management.domain.client.Client;
import com.pharmacy.management.domain.client.ClientRepository;

import java.util.Objects;
import java.util.Optional;

public class DefaultGetClientById extends GetClientById {

    private final ClientRepository clientRepository;

    public DefaultGetClientById(final ClientRepository clientRepository) {
        this.clientRepository = Objects.requireNonNull(clientRepository);
    }

    @Override
    public Optional<Output> execute(final Input anIn) {
        return this.clientRepository.findById(anIn.id())
                .map(StdOutput::from);
    }

    record StdOutput(String id, String name, String email, String cpf, String phoneNumber)
            implements Output {
        public static StdOutput from(final Client client) {
            return new StdOutput(client.id(), client.name(), client.email(), client.cpf(), client.phoneNumber());
        }
    }
}
