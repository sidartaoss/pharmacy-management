package com.pharmacy.management.infrastructure.rest.models.res;

import com.pharmacy.management.application.subscription.ListSubscription;

import java.math.BigDecimal;

public record SubscriptionResponse(
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

    public SubscriptionResponse(final ListSubscription.Output anOut) {
        this(anOut.id(), anOut.clientId(), anOut.clientName(), anOut.clientEmail(), anOut.clientPhoneNumber(),
                anOut.medicationId(), anOut.medicationName(), anOut.medicationBrand(), anOut.medicationDosage(),
                anOut.medicationMonthlyRenewalDay());
    }
}
