package com.pharmacy.management.application.client.impl;

import com.pharmacy.management.application.client.AttachMedication;
import com.pharmacy.management.domain.client.Client;
import com.pharmacy.management.domain.client.ClientRepository;
import com.pharmacy.management.domain.exceptions.NotFoundException;
import com.pharmacy.management.domain.medication.Medication;
import com.pharmacy.management.domain.medication.MedicationRepository;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultAttachMedication extends AttachMedication {

    private final MedicationRepository medicationRepository;
    private final ClientRepository clientRepository;

    public DefaultAttachMedication(
            final MedicationRepository medicationRepository,
            final ClientRepository clientRepository) {
        this.medicationRepository = Objects.requireNonNull(medicationRepository);
        this.clientRepository = Objects.requireNonNull(clientRepository);
    }

    @Override
    public void execute(final Input anIn) {
        final var aClient = this.clientRepository.findById(anIn.clientId())
                .orElseThrow(notFound(Client.class, anIn.clientId()));

        final var aMedication = this.medicationRepository.findById(anIn.medicationId())
                .orElseThrow(notFound(Medication.class, anIn.medicationId()));

        aClient.attachMedication(aMedication.id(), anIn.monthlyRenewalDay());
        clientRepository.save(aClient);
    }

    private Supplier<NotFoundException> notFound(final Class<?> aClazz, final String anId) {
        return () -> NotFoundException.with("%s with ID %s not found"
                .formatted(aClazz.getSimpleName(), anId));
    }
}
