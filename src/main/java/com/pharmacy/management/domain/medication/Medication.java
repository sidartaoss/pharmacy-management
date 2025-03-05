package com.pharmacy.management.domain.medication;

import java.math.BigDecimal;

public class Medication {

    private String id;
    private String name;
    private String brand;
    private BigDecimal price;
    private BigDecimal dosage;

    private Medication(
            final String id,
            final String name,
            final String brand,
            final BigDecimal price,
            final BigDecimal dosage
    ) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.dosage = dosage;
    }

    public static Medication newMedication(
            final String id,
            final String name,
            final String brand,
            final BigDecimal price,
            final BigDecimal dosage
    ) {
        return new Medication(id, name, brand, price, dosage);
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String brand() {
        return brand;
    }

    public BigDecimal price() {
        return price;
    }

    public BigDecimal dosage() {
        return dosage;
    }
}
