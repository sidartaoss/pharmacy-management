package com.pharmacy.management.infrastructure.repositories;

import com.pharmacy.management.domain.client.ClientMedication;
import com.pharmacy.management.domain.client.ClientMedicationRepository;
import com.pharmacy.management.infrastructure.mongodb.documents.ClientMedicationDocument;
import com.pharmacy.management.infrastructure.mongodb.repositories.ClientMedicationMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientMedicationDatabaseRepository implements ClientMedicationRepository {

    private final ClientMedicationMongoRepository clientMedicationMongoRepository;

    public ClientMedicationDatabaseRepository(
            final ClientMedicationMongoRepository clientMedicationMongoRepository
    ) {
        this.clientMedicationMongoRepository = clientMedicationMongoRepository;
    }

    @Override
    public String nextId() {
        return null;
    }

    @Override
    public ClientMedication save(final ClientMedication aClientMedication) {
        return this.clientMedicationMongoRepository.save(ClientMedicationDocument.of(aClientMedication))
                .toDomain();
    }

    @Override
    public Optional<ClientMedication> findByClientIdAndMedicationId(final String clientId, final String medicationId) {
        return this.clientMedicationMongoRepository.findByClientIdAndMedicationId(clientId, medicationId)
                .map(ClientMedicationDocument::toDomain);
    }
}
