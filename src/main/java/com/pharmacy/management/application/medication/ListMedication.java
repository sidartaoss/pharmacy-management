package com.pharmacy.management.application.medication;

import com.pharmacy.management.application.NullaryUseCase;

import java.math.BigDecimal;
import java.util.Set;

public abstract class ListMedication
        extends NullaryUseCase<Set<ListMedication.Output>> {

    public interface Output {
        String id();

        String name();

        String brand();

        BigDecimal price();

        BigDecimal dosage();
    }
}