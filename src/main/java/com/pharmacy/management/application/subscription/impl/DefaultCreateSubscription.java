package com.pharmacy.management.application.subscription.impl;

import com.pharmacy.management.application.subscription.CreateSubscription;
import com.pharmacy.management.domain.client.Client;
import com.pharmacy.management.domain.client.ClientRepository;
import com.pharmacy.management.domain.exceptions.NotFoundException;
import com.pharmacy.management.domain.medication.Medication;
import com.pharmacy.management.domain.medication.MedicationRepository;
import com.pharmacy.management.domain.subscription.Subscription;
import com.pharmacy.management.domain.subscription.SubscriptionRepository;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultCreateSubscription extends CreateSubscription {

    private final ClientRepository clientRepository;
    private final MedicationRepository medicationRepository;
    private final SubscriptionRepository subscriptionRepository;

    public DefaultCreateSubscription(
            final ClientRepository clientRepository,
            final MedicationRepository medicationRepository,
            final SubscriptionRepository subscriptionRepository) {
        this.clientRepository = Objects.requireNonNull(clientRepository);
        this.medicationRepository = Objects.requireNonNull(medicationRepository);
        this.subscriptionRepository = Objects.requireNonNull(subscriptionRepository);
    }

    @Override
    public Output execute(final Input anIn) {
        final var aClient = this.clientRepository.findById(anIn.clientId())
                .orElseThrow(notFound(Client.class, anIn.clientId()));

        final var aMedication = this.medicationRepository.findById(anIn.medicationId())
                .orElseThrow(notFound(Medication.class, anIn.medicationId()));

        final var aSubscription = this.subscriptionRepository.save(
                this.newSubscriptionWith(anIn, aClient, aMedication));
        return new StdOutput(aSubscription.id());
    }

    private Subscription newSubscriptionWith(
            final Input anIn,
            final Client aClient,
            final Medication aMedication
    ) {
        return new Subscription(
                this.subscriptionRepository.nextId(),
                aClient.id(),
                aClient.name(),
                aClient.email(),
                aClient.phoneNumber(),
                aMedication.id(),
                aMedication.name(),
                aMedication.brand(),
                aMedication.dosage(),
                anIn.monthlyRenewalDay()
        );
    }

    record StdOutput(String subscriptionId) implements Output {
    }

    private Supplier<NotFoundException> notFound(final Class<?> aClazz, final String anId) {
        return () -> NotFoundException.with("%s with ID %s not found"
                .formatted(aClazz.getSimpleName(), anId));
    }
}