package com.pharmacy.management.infrastructure.repositories;

import com.pharmacy.management.domain.client.Client;
import com.pharmacy.management.domain.client.ClientRepository;
import com.pharmacy.management.infrastructure.configuration.annotations.MedicationAttachedQueue;
import com.pharmacy.management.infrastructure.mongodb.documents.ClientDocument;
import com.pharmacy.management.infrastructure.mongodb.repositories.ClientMongoRepository;
import com.pharmacy.management.infrastructure.services.EventService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class ClientDatabaseRepository implements ClientRepository {

    private final EventService eventService;
    private final ClientMongoRepository clientMongoRepository;

    public ClientDatabaseRepository(
            @MedicationAttachedQueue final EventService eventService,
            final ClientMongoRepository clientMongoRepository
    ) {
        this.eventService = Objects.requireNonNull(eventService);
        this.clientMongoRepository = Objects.requireNonNull(clientMongoRepository);
    }

    @Override
    public String nextId() {
        return null;
    }

    @Transactional
    @Override
    public Client save(final Client aClient) {
        return this.clientMongoRepository.save(ClientDocument.of(aClient))
                .toDomain();
    }

    @Override
    public Optional<Client> findById(final String id) {
        return this.clientMongoRepository.findById(id)
                .map(ClientDocument::toDomain);
    }

    @Override
    public Set<Client> findAll() {
        return StreamSupport.stream(this.clientMongoRepository.findAll().spliterator(), false)
                .map(ClientDocument::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public void deleteById(final String id) {
        this.clientMongoRepository.deleteById(id);
    }
}
