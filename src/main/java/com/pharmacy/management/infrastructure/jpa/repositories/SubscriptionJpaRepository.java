package com.pharmacy.management.infrastructure.jpa.repositories;

import com.pharmacy.management.infrastructure.jpa.entities.SubscriptionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface SubscriptionJpaRepository extends CrudRepository<SubscriptionEntity, String> {

    Set<SubscriptionEntity> findByClientEmail(String clientEmail);
}
