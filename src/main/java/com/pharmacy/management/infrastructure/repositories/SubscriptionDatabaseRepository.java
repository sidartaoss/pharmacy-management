package com.pharmacy.management.infrastructure.repositories;

import com.pharmacy.management.domain.subscription.Subscription;
import com.pharmacy.management.domain.subscription.SubscriptionRepository;
import com.pharmacy.management.domain.utils.CollectionUtils;
import com.pharmacy.management.infrastructure.jpa.entities.SubscriptionEntity;
import com.pharmacy.management.infrastructure.jpa.repositories.SubscriptionJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Set;

@Repository
public class SubscriptionDatabaseRepository implements SubscriptionRepository {

    private final SubscriptionJpaRepository subscriptionJpaRepository;

    public SubscriptionDatabaseRepository(final SubscriptionJpaRepository subscriptionJpaRepository) {
        this.subscriptionJpaRepository = Objects.requireNonNull(subscriptionJpaRepository);
    }

    @Override
    public String nextId() {
        return null;
    }

    @Override
    public Subscription save(final Subscription aSubscription) {
        return this.subscriptionJpaRepository.save(SubscriptionEntity.from(aSubscription))
                .toDomain();
    }

    @Override
    public Set<Subscription> listBy(final String clientEmail) {
        return CollectionUtils.map(this.subscriptionJpaRepository.findByClientEmail(clientEmail),
                SubscriptionEntity::toDomain);
    }
}
