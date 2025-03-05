package com.pharmacy.management.application.medication.impl;

import com.pharmacy.management.application.medication.DeleteMedication;
import com.pharmacy.management.domain.medication.MedicationRepository;

import java.util.Objects;

public class DefaultDeleteMedication extends DeleteMedication {

    private final MedicationRepository medicationRepository;

    public DefaultDeleteMedication(final MedicationRepository medicationRepository) {
        this.medicationRepository = Objects.requireNonNull(medicationRepository);
    }

    @Override
    public void execute(final Input anIn) {
        this.medicationRepository.deleteById(anIn.id());
    }
}
