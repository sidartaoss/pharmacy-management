package com.pharmacy.management.application.medication.impl;

import com.pharmacy.management.application.medication.UpdateMedication;
import com.pharmacy.management.domain.exceptions.NotFoundException;
import com.pharmacy.management.domain.medication.Medication;
import com.pharmacy.management.domain.medication.MedicationRepository;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultUpdateMedication extends UpdateMedication {

    private final MedicationRepository medicationRepository;

    public DefaultUpdateMedication(final MedicationRepository medicationRepository) {
        this.medicationRepository = Objects.requireNonNull(medicationRepository);
    }

    @Override
    public Output execute(final Input anIn) {
        final var aMedication = this.medicationRepository.findById(anIn.id())
                .orElseThrow(notFound(anIn));
        this.medicationRepository.save(
                this.newMedicationWith(anIn));
        return new StdOutput(aMedication.id());
    }

    record StdOutput(String medicationId) implements UpdateMedication.Output {
    }

    private Medication newMedicationWith(UpdateMedication.Input anIn) {
        return Medication.newMedication(
                anIn.id(),
                anIn.name(),
                anIn.brand(),
                anIn.price(),
                anIn.dosage()
        );
    }

    private Supplier<NotFoundException> notFound(final Input anIn) {
        return () -> NotFoundException.with("Medication with id %s could not be found".formatted(anIn.id()));
    }
}