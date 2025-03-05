package com.pharmacy.management.application.medication.impl;

import com.pharmacy.management.application.medication.ListMedication;
import com.pharmacy.management.domain.medication.Medication;
import com.pharmacy.management.domain.medication.MedicationRepository;
import com.pharmacy.management.domain.utils.CollectionUtils;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class DefaultListMedication extends ListMedication {

    private final MedicationRepository medicationRepository;

    public DefaultListMedication(final MedicationRepository medicationRepository) {
        this.medicationRepository = Objects.requireNonNull(medicationRepository);
    }

    @Override
    public Set<Output> execute() {
        return CollectionUtils.map(this.medicationRepository.findAll(), StdOutput::from);
    }

    record StdOutput(String id, String name, String brand, BigDecimal price, BigDecimal dosage)
            implements Output {
        public static StdOutput from(final Medication medication) {
            return new StdOutput(medication.id(), medication.name(), medication.brand(), medication.price(),
                    medication.dosage());
        }
    }
}