package com.pharmacy.management.domain.subscription;

import java.util.Set;

public interface SubscriptionRepository {

    String nextId();

    Subscription save(Subscription subscription);

    Set<Subscription> listBy(String clientEmail);
}
