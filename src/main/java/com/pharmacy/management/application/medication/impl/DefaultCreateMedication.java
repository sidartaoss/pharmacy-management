package com.pharmacy.management.application.medication.impl;

import com.pharmacy.management.application.medication.CreateMedication;
import com.pharmacy.management.domain.medication.Medication;
import com.pharmacy.management.domain.medication.MedicationRepository;

import java.util.Objects;

public class DefaultCreateMedication extends CreateMedication {

    private final MedicationRepository medicationRepository;

    public DefaultCreateMedication(final MedicationRepository medicationRepository) {
        this.medicationRepository = Objects.requireNonNull(medicationRepository);
    }

    @Override
    public Output execute(final Input anIn) {
        final var aMedication = this.medicationRepository.save(
                this.newMedicationWith(anIn));
        return new StdOutput(aMedication.id());
    }

    private Medication newMedicationWith(Input anIn) {
        return Medication.newMedication(
                this.medicationRepository.nextId(),
                anIn.name(),
                anIn.brand(),
                anIn.price(),
                anIn.dosage()
        );
    }

    record StdOutput(String medicationId) implements Output {
    }
}
