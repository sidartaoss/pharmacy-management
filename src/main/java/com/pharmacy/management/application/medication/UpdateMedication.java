package com.pharmacy.management.application.medication;

import com.pharmacy.management.application.UseCase;

import java.math.BigDecimal;

public abstract class UpdateMedication
        extends UseCase<UpdateMedication.Input, UpdateMedication.Output> {

    public interface Input {
        String id();

        String name();

        String brand();

        BigDecimal price();

        BigDecimal dosage();
    }

    public interface Output {
        String medicationId();
    }
}