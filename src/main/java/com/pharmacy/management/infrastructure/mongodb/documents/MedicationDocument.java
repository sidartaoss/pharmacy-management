package com.pharmacy.management.infrastructure.mongodb.documents;

import com.pharmacy.management.domain.medication.Medication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "medications")
public class MedicationDocument {

    @Id
    private String id;

    private String name;

    private String brand;

    private BigDecimal price;

    private BigDecimal dosage;

    public MedicationDocument() {
    }

    private MedicationDocument(
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

    public static MedicationDocument from(final Medication aMedication) {
        return new MedicationDocument(
                aMedication.id(),
                aMedication.name(),
                aMedication.brand(),
                aMedication.price(),
                aMedication.dosage()
        );
    }

    public Medication toDomain() {
        return Medication.newMedication(
                this.id,
                this.name,
                this.brand,
                this.price,
                this.dosage
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDosage() {
        return dosage;
    }

    public void setDosage(BigDecimal dosage) {
        this.dosage = dosage;
    }
}