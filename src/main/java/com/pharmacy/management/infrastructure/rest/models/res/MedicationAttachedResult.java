package com.pharmacy.management.infrastructure.rest.models.res;

import java.time.Instant;

public record MedicationAttachedResult(
        String domainEventId,
        String type,
        String clientId,
        String medicationId,
        Instant occurredOn
) {
}

