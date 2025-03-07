package com.pharmacy.management.application.client.impl;

import com.pharmacy.management.application.client.CreateClientMedication;
import com.pharmacy.management.domain.client.ClientMedication;
import com.pharmacy.management.domain.client.ClientMedicationRepository;

import java.util.Objects;

public class DefaultCreateClientMedication extends CreateClientMedication {

    private final ClientMedicationRepository clientMedicationRepository;

    public DefaultCreateClientMedication(final ClientMedicationRepository clientMedicationRepository) {
        this.clientMedicationRepository = Objects.requireNonNull(clientMedicationRepository);
    }

    @Override
    public Output execute(final Input anIn) {
        final var aClientMedication = this.clientMedicationRepository.save(
                this.newClientMedicationWith(anIn));
        return new StdOutput(aClientMedication.id());
    }

    private ClientMedication newClientMedicationWith(Input anIn) {
        return ClientMedication.newClientMedication(
                this.clientMedicationRepository.nextId(),
                anIn.clientId(),
                anIn.medicationId(),
                anIn.monthlyRenewalDay()
        );
    }

    record StdOutput(String clientMedicationId) implements Output {
    }
}
