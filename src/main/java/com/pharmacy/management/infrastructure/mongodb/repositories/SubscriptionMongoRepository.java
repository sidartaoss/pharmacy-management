package com.pharmacy.management.infrastructure.mongodb.repositories;

import com.pharmacy.management.infrastructure.mongodb.documents.SubscriptionDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Set;

public interface SubscriptionMongoRepository extends MongoRepository<SubscriptionDocument, String> {

    Set<SubscriptionDocument> findByClientEmail(String clientEmail);
}
