package com.pharmacy.management.domain.subscription;

import java.math.BigDecimal;

public class Subscription {

    private String id;
    private String clientId;
    private String clientName;
    private String clientEmail;
    private String clientPhoneNumber;
    private String medicationId;
    private String medicationName;
    private String medicationBrand;
    private BigDecimal medicationDosage;
    private Integer medicationMonthlyRenewalDay;

    private Subscription(
            String id,
            String clientId,
            String clientName,
            String clientEmail,
            String clientPhoneNumber,
            String medicationId,
            String medicationName,
            String medicationBrand,
            BigDecimal medicationDosage,
            Integer medicationMonthlyRenewalDay
    ) {
        this.id = id;
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.clientPhoneNumber = clientPhoneNumber;
        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.medicationBrand = medicationBrand;
        this.medicationDosage = medicationDosage;
        this.medicationMonthlyRenewalDay = medicationMonthlyRenewalDay;
    }

    public static Subscription newSubscription(
            final String id,
            final String clientId,
            final String clientName,
            final String clientEmail,
            final String clientPhoneNumber,
            final String medicationId,
            final String medicationName,
            final String medicationBrand,
            final BigDecimal medicationDosage,
            final Integer medicationMonthlyRenewalDay
    ) {
        return new Subscription(
                id,
                clientId,
                clientName,
                clientEmail,
                clientPhoneNumber,
                medicationId,
                medicationName,
                medicationBrand,
                medicationDosage,
                medicationMonthlyRenewalDay
        );
    }

    public String id() {
        return id;
    }

    public String clientId() {
        return clientId;
    }

    public String clientName() {
        return clientName;
    }

    public String clientEmail() {
        return clientEmail;
    }

    public String clientPhoneNumber() {
        return clientPhoneNumber;
    }

    public String medicationId() {
        return medicationId;
    }

    public String medicationName() {
        return medicationName;
    }

    public String medicationBrand() {
        return medicationBrand;
    }

    public BigDecimal medicationDosage() {
        return medicationDosage;
    }

    public Integer medicationMonthlyRenewalDay() {
        return medicationMonthlyRenewalDay;
    }
}
