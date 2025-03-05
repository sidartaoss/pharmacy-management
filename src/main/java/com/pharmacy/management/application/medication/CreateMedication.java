package com.pharmacy.management.application.medication;

import com.pharmacy.management.application.UseCase;

import java.math.BigDecimal;

public abstract class CreateMedication
        extends UseCase<CreateMedication.Input, CreateMedication.Output> {

    public interface Input {
        String name();

        String brand();

        BigDecimal price();

        BigDecimal dosage();
    }

    public interface Output {
        String medicationId();
    }
}
