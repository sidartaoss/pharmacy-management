package com.pharmacy.management.domain.client;

import com.pharmacy.management.domain.event.DomainEvent;
import com.pharmacy.management.domain.event.DomainEventPublisher;
import com.pharmacy.management.domain.medication.MedicationAttached;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Client {

    private String id;
    private String name;
    private String email;
    private String cpf;
    private String phoneNumber;
    private final Set<ClientMedication> medications;
    private final Set<DomainEvent> domainEvents;

    private Client(final String eventId, final Set<ClientMedication> aMedications) {
        this.id = eventId;
        this.medications = aMedications != null ? aMedications : HashSet.newHashSet(0);
        this.domainEvents = HashSet.newHashSet(2);
    }

    private Client(String id, String name, String email, String cpf, String phoneNumber,
                   Set<ClientMedication> medications) {
        this(id, medications);
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
    }

    public void attachMedication(
            final String medicationId,
            final Integer monthlyRenewalDay
    ) {
        final var aMedication = ClientMedication.newClientMedication(
                id(),
                medicationId,
                monthlyRenewalDay);
        this.medications.add(aMedication);
        this.registerEvent(new MedicationAttached(
                id(), medicationId, monthlyRenewalDay)
        );
    }

    public static Client newClient(String id, String name, String email, String cpf, String phoneNumber) {
        return new Client(id, name, email, cpf, phoneNumber, new HashSet<>());
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String email() {
        return email;
    }

    public String cpf() {
        return cpf;
    }

    public String phoneNumber() {
        return phoneNumber;
    }

    public Set<ClientMedication> allMedications() {
        return Collections.unmodifiableSet(medications);
    }

    public void publishDomainEvents(final DomainEventPublisher publisher) {
        if (publisher == null) {
            return;
        }
        allDomainEvents()
                .forEach(publisher::publishEvent);
        this.domainEvents.clear();
    }

    private Set<DomainEvent> allDomainEvents() {
        return Collections.unmodifiableSet(domainEvents);
    }

    private void registerEvent(final DomainEvent event) {
        if (event == null) {
            return;
        }
        this.domainEvents.add(event);
    }
}
