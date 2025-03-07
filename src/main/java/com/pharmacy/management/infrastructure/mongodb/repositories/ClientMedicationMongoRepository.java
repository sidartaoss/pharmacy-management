package com.pharmacy.management.infrastructure.mongodb.repositories;

import com.pharmacy.management.infrastructure.mongodb.documents.ClientMedicationDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClientMedicationMongoRepository extends MongoRepository<ClientMedicationDocument, String> {

    Optional<ClientMedicationDocument> findByClientIdAndMedicationId(String clientId, String medicationId);
}
