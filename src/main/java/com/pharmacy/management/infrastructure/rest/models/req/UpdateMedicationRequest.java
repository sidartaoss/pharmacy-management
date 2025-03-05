package com.pharmacy.management.infrastructure.rest.models.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdateMedicationRequest(
        @NotBlank @Size(max = 255) String name,
        @NotBlank @Size(max = 255) String brand,
        @NotNull BigDecimal price,
        @NotNull BigDecimal dosage
) {
}
