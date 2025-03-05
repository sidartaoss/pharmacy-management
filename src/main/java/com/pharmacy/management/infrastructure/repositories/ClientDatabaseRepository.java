package com.pharmacy.management.infrastructure.repositories;

import com.pharmacy.management.domain.client.Client;
import com.pharmacy.management.domain.client.ClientRepository;
import com.pharmacy.management.infrastructure.configuration.annotations.MedicationAttachedQueue;
import com.pharmacy.management.infrastructure.jpa.entities.ClientEntity;
import com.pharmacy.management.infrastructure.jpa.repositories.ClientJpaRepository;
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
    private final ClientJpaRepository clientJpaRepository;

    public ClientDatabaseRepository(
            @MedicationAttachedQueue final EventService eventService,
            final ClientJpaRepository clientJpaRepository
    ) {
        this.eventService = Objects.requireNonNull(eventService);
        this.clientJpaRepository = Objects.requireNonNull(clientJpaRepository);
    }

    @Override
    public String nextId() {
        return null;
    }

    @Transactional
    @Override
    public Client save(final Client aClient) {
        final var result = this.clientJpaRepository.save(ClientEntity.of(aClient))
                .toDomain();
        aClient.publishDomainEvents(this.eventService::send);
        return result;
    }

    @Override
    public Optional<Client> findById(final String id) {
        return this.clientJpaRepository.findById(id)
                .map(ClientEntity::toDomain);
    }

    @Override
    public Set<Client> findAll() {
        return StreamSupport.stream(this.clientJpaRepository.findAll().spliterator(), false)
                .map(ClientEntity::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public void deleteById(final String id) {
        this.clientJpaRepository.deleteById(id);
    }
}
