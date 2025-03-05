package com.pharmacy.management.infrastructure.rest.models.res;

import com.pharmacy.management.application.medication.ListMedication;

import java.math.BigDecimal;

public record MedicationResponse(
        String id,
        String name,
        String brand,
        BigDecimal price,
        BigDecimal dosage
) {

    public MedicationResponse(final ListMedication.Output anOut) {
        this(anOut.id(), anOut.name(), anOut.brand(), anOut.price(), anOut.dosage());
    }
}