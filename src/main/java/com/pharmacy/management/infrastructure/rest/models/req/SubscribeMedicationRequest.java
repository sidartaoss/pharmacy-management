package com.pharmacy.management.infrastructure.rest.models.req;

public record SubscribeMedicationRequest(
        String medicationId,
        Integer monthlyRenewalDay
) {
}
