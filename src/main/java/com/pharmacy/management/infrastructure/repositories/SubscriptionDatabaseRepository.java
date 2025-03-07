package com.pharmacy.management.infrastructure.repositories;

import com.pharmacy.management.domain.subscription.Subscription;
import com.pharmacy.management.domain.subscription.SubscriptionRepository;
import com.pharmacy.management.domain.utils.CollectionUtils;
import com.pharmacy.management.infrastructure.mongodb.documents.SubscriptionDocument;
import com.pharmacy.management.infrastructure.mongodb.repositories.SubscriptionMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Set;

@Repository
public class SubscriptionDatabaseRepository implements SubscriptionRepository {

    private final SubscriptionMongoRepository subscriptionJpaRepository;

    public SubscriptionDatabaseRepository(final SubscriptionMongoRepository subscriptionJpaRepository) {
        this.subscriptionJpaRepository = Objects.requireNonNull(subscriptionJpaRepository);
    }

    @Override
    public String nextId() {
        return null;
    }

    @Override
    public Subscription save(final Subscription aSubscription) {
        return this.subscriptionJpaRepository.save(SubscriptionDocument.from(aSubscription))
                .toDomain();
    }

    @Override
    public Set<Subscription> listBy(final String clientEmail) {
        return CollectionUtils.map(this.subscriptionJpaRepository.findByClientEmail(clientEmail),
                SubscriptionDocument::toDomain);
    }
}
