package com.pharmacy.management.infrastructure.rest.models.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record MedicationAttachedResult(
        @JsonProperty("domain_event_id") String domainEventId,
        @JsonProperty("type") String type,
        @JsonProperty("client_id") String clientId,
        @JsonProperty("medication_id") String medicationId,
        @JsonProperty("monthly_renewal_day") Integer monthlyRenewalDay,
        @JsonProperty("occurred_on") Instant occurredOn
) {
}

