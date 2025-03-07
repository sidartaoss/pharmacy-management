package com.pharmacy.management.application.client.impl;

import com.pharmacy.management.application.client.AttachMedication;
import com.pharmacy.management.domain.client.ClientMedicationRepository;
import com.pharmacy.management.domain.client.ClientRepository;
import com.pharmacy.management.domain.exceptions.NotFoundException;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultAttachMedication extends AttachMedication {

    private final ClientRepository clientRepository;
    private final ClientMedicationRepository clientMedicationRepository;

    public DefaultAttachMedication(
            final ClientRepository clientRepository,
            final ClientMedicationRepository clientMedicationRepository
    ) {
        this.clientRepository = Objects.requireNonNull(clientRepository);
        this.clientMedicationRepository = Objects.requireNonNull(clientMedicationRepository);
    }

    @Override
    public void execute(final Input anIn) {
        final var aClient = this.clientRepository.findById(anIn.clientId())
                .orElseThrow(notFound(anIn.clientId()));
        final var aClientMedication = this.clientMedicationRepository.findByClientIdAndMedicationId(
                        anIn.clientId(), anIn.medicationId())
                .orElseThrow(notFound(anIn.clientId(), anIn.medicationId()));

        aClient.attachMedication(aClientMedication.id(), anIn.medicationId(), anIn.monthlyRenewalDay());
        clientRepository.save(aClient);
    }

    private Supplier<NotFoundException> notFound(final String aClientId, final String aMedicationId) {
        return () -> NotFoundException.with("ClientMedication with Client ID %s and Medication ID %s not found"
                .formatted(aClientId, aMedicationId));
    }

    private Supplier<NotFoundException> notFound(final String aClientId) {
        return () -> NotFoundException.with("Client with ID %s not found"
                .formatted(aClientId));
    }
}
