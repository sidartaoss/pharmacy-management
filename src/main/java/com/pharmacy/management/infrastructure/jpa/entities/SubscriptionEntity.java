package com.pharmacy.management.infrastructure.jpa.entities;

import com.pharmacy.management.domain.subscription.Subscription;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "subscriptions")
public class SubscriptionEntity {

    @Id
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

    public SubscriptionEntity() {
    }

    private SubscriptionEntity(
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

    public static SubscriptionEntity from(final Subscription aSubscription) {
        return new SubscriptionEntity(
                aSubscription.id(),
                aSubscription.clientId(),
                aSubscription.clientName(),
                aSubscription.clientEmail(),
                aSubscription.clientPhoneNumber(),
                aSubscription.medicationId(),
                aSubscription.medicationName(),
                aSubscription.medicationBrand(),
                aSubscription.medicationDosage(),
                aSubscription.medicationMonthlyRenewalDay()
        );
    }

    public Subscription toDomain() {
        return new Subscription(
                this.id,
                this.clientId,
                this.clientName,
                this.clientEmail,
                this.clientPhoneNumber,
                this.medicationId,
                this.medicationName,
                this.medicationBrand,
                this.medicationDosage,
                this.medicationMonthlyRenewalDay
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public String getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getMedicationBrand() {
        return medicationBrand;
    }

    public void setMedicationBrand(String medicationBrand) {
        this.medicationBrand = medicationBrand;
    }

    public BigDecimal getMedicationDosage() {
        return medicationDosage;
    }

    public void setMedicationDosage(BigDecimal medicationDosage) {
        this.medicationDosage = medicationDosage;
    }

    public Integer getMedicationMonthlyRenewalDay() {
        return medicationMonthlyRenewalDay;
    }

    public void setMedicationMonthlyRenewalDay(Integer medicationMonthlyRenewalDay) {
        this.medicationMonthlyRenewalDay = medicationMonthlyRenewalDay;
    }
}