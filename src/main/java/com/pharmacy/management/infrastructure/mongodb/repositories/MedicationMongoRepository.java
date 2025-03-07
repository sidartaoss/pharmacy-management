package com.pharmacy.management.infrastructure.mongodb.repositories;

import com.pharmacy.management.infrastructure.mongodb.documents.MedicationDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicationMongoRepository extends MongoRepository<MedicationDocument, String> {
}
