package com.pharmacy.management.infrastructure.repositories;

import com.pharmacy.management.domain.medication.Medication;
import com.pharmacy.management.domain.medication.MedicationRepository;
import com.pharmacy.management.infrastructure.jpa.entities.MedicationEntity;
import com.pharmacy.management.infrastructure.jpa.repositories.MedicationJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class MedicationDatabaseRepository implements MedicationRepository {

    private final MedicationJpaRepository medicationJpaRepository;

    public MedicationDatabaseRepository(final MedicationJpaRepository medicationJpaRepository) {
        this.medicationJpaRepository = Objects.requireNonNull(medicationJpaRepository);
    }

    @Override
    public String nextId() {
        return null;
    }

    @Override
    public Medication save(final Medication aMedication) {
        return this.medicationJpaRepository.save(MedicationEntity.from(aMedication))
                .toDomain();
    }

    @Override
    public Optional<Medication> findById(final String id) {
        return this.medicationJpaRepository.findById(id)
                .map(MedicationEntity::toDomain);
    }

    @Override
    public Set<Medication> findAll() {
        return StreamSupport.stream(this.medicationJpaRepository.findAll().spliterator(), false)
                .map(MedicationEntity::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public void deleteById(final String id) {
        this.medicationJpaRepository.deleteById(id);
    }
}