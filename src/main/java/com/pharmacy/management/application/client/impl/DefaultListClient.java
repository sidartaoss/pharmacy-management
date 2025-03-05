package com.pharmacy.management.application.client.impl;

import com.pharmacy.management.application.client.ListClient;
import com.pharmacy.management.domain.client.Client;
import com.pharmacy.management.domain.client.ClientRepository;
import com.pharmacy.management.domain.utils.CollectionUtils;

import java.util.Objects;
import java.util.Set;

public class DefaultListClient extends ListClient {

    private final ClientRepository clientRepository;

    public DefaultListClient(final ClientRepository clientRepository) {
        this.clientRepository = Objects.requireNonNull(clientRepository);
    }

    @Override
    public Set<Output> execute() {
        return CollectionUtils.map(this.clientRepository.findAll(), StdOutput::from);
    }

    record StdOutput(String id, String name, String email, String cpf, String phoneNumber)
            implements Output {
        public static StdOutput from(final Client client) {
            return new StdOutput(client.id(), client.name(), client.email(), client.cpf(), client.phoneNumber());
        }
    }
}
