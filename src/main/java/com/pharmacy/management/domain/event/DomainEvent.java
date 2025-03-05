package com.pharmacy.management.domain.event;

import java.time.Instant;

public interface DomainEvent {

    String domainEventId();

    String type();

    Instant occurredOn();
}
