package com.pharmacy.management.infrastructure.configuration.usecases;

import com.pharmacy.management.application.subscription.CreateSubscription;
import com.pharmacy.management.application.subscription.ListSubscription;
import com.pharmacy.management.application.subscription.impl.DefaultCreateSubscription;
import com.pharmacy.management.application.subscription.impl.DefaultListSubscription;
import com.pharmacy.management.domain.client.ClientRepository;
import com.pharmacy.management.domain.medication.MedicationRepository;
import com.pharmacy.management.domain.subscription.SubscriptionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class SubscriptionUseCaseConfig {

    @Bean
    public ListSubscription listSubscription(final SubscriptionRepository subscriptionRepository) {
        return new DefaultListSubscription(subscriptionRepository);
    }

    @Bean
    public CreateSubscription createSubscription(
            final ClientRepository clientRepository,
            final MedicationRepository medicationRepository,
            final SubscriptionRepository subscriptionRepository
    ) {
        return new DefaultCreateSubscription(clientRepository, medicationRepository, subscriptionRepository);
    }
}
