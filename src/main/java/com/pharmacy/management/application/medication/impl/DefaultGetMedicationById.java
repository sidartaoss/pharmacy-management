package com.pharmacy.management.application.medication.impl;

import com.pharmacy.management.application.medication.GetMedicationById;
import com.pharmacy.management.domain.medication.Medication;
import com.pharmacy.management.domain.medication.MedicationRepository;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

public class DefaultGetMedicationById extends GetMedicationById {

    private final MedicationRepository medicationRepository;

    public DefaultGetMedicationById(final MedicationRepository medicationRepository) {
        this.medicationRepository = Objects.requireNonNull(medicationRepository);
    }

    @Override
    public Optional<Output> execute(final Input anIn) {
        return this.medicationRepository.findById(anIn.id())
                .map(StdOutput::from);
    }

    record StdOutput(String id, String name, String brand, BigDecimal price, BigDecimal dosage)
            implements Output {
        public static StdOutput from(final Medication medication) {
            return new StdOutput(medication.id(), medication.name(), medication.brand(), medication.price(),
                    medication.dosage());
        }
    }
}