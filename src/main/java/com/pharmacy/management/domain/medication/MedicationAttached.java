package com.pharmacy.management.domain.medication;

import com.pharmacy.management.domain.event.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record MedicationAttached(
        String domainEventId,
        String type,
        String clientId,
        String medicationId,
        Instant occurredOn
) implements DomainEvent {

    public MedicationAttached(
            String clientId,
            String medicationId
    ) {
        this(UUID.randomUUID().toString(),
                "medication.attached",
                clientId,
                medicationId,
                Instant.now());
    }
}
