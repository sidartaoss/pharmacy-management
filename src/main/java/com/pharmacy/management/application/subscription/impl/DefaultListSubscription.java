package com.pharmacy.management.application.subscription.impl;

import com.pharmacy.management.application.subscription.ListSubscription;
import com.pharmacy.management.domain.subscription.Subscription;
import com.pharmacy.management.domain.subscription.SubscriptionRepository;
import com.pharmacy.management.domain.utils.CollectionUtils;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class DefaultListSubscription extends ListSubscription {

    private final SubscriptionRepository subscriptionRepository;

    public DefaultListSubscription(final SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = Objects.requireNonNull(subscriptionRepository);
    }

    @Override
    public Set<Output> execute(final Input input) {
        return CollectionUtils.map(this.subscriptionRepository.listBy(input.clientEmail()),
                StdOutput::from);
    }

    record StdOutput(
            String id,
            String clientId,
            String clientName,
            String clientEmail,
            String clientPhoneNumber,
            String medicationId,
            String medicationName,
            String medicationBrand,
            BigDecimal medicationDosage,
            Integer medicationMonthlyRenewalDay) implements ListSubscription.Output {
        public static StdOutput from(final Subscription subscription) {
            return new StdOutput(subscription.id(), subscription.clientId(), subscription.clientName(),
                    subscription.clientEmail(), subscription.clientPhoneNumber(), subscription.medicationId(),
                    subscription.medicationName(), subscription.medicationBrand(), subscription.medicationDosage(),
                    subscription.medicationMonthlyRenewalDay());
        }
    }
}