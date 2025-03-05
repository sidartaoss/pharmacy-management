package com.pharmacy.management.application.medication;

import com.pharmacy.management.application.UseCase;

import java.math.BigDecimal;
import java.util.Optional;

public abstract class GetMedicationById
        extends UseCase<GetMedicationById.Input, Optional<GetMedicationById.Output>> {

    public interface Input {
        String id();
    }

    public interface Output {
        String id();

        String name();

        String brand();

        BigDecimal price();

        BigDecimal dosage();
    }
}