package com.pharmacy.management.infrastructure.rest.models.res;

import com.pharmacy.management.application.client.ListClient;

public record ClientResponse(
        String id,
        String name,
        String email,
        String cpf,
        String phoneNumber
) {

    public ClientResponse(final ListClient.Output anOut) {
        this(anOut.id(), anOut.name(), anOut.email(), anOut.cpf(), anOut.phoneNumber());
    }
}
