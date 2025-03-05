package com.pharmacy.management.infrastructure.rest.models.req;

public record SubscribeMedicationRequest(
        String clientId,
        String medicationId,
        Integer monthlyRenewalDay
) {
}
