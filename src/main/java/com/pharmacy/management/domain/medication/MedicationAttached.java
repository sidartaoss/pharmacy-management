package com.pharmacy.management.domain.medication;

import com.pharmacy.management.domain.event.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record MedicationAttached(
        String domainEventId,
        String type,
        String clientId,
        String medicationId,
        Integer monthlyRenewalDay,
        Instant occurredOn
) implements DomainEvent {

    public MedicationAttached(
            String clientId,
            String medicationId,
            Integer monthlyRenewalDay
    ) {
        this(UUID.randomUUID().toString(),
                "medication.attached",
                clientId,
                medicationId,
                monthlyRenewalDay,
                Instant.now());
    }
}
