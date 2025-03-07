package com.pharmacy.management.infrastructure.mongodb.repositories;

import com.pharmacy.management.infrastructure.mongodb.documents.ClientDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientMongoRepository extends MongoRepository<ClientDocument, String> {

}
