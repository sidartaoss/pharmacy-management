package com.pharmacy.management.domain.medication;

import java.util.Optional;
import java.util.Set;

public interface MedicationRepository {

    String nextId();

    Medication save(Medication medication);

    Optional<Medication> findById(String id);

    Set<Medication> findAll();

    void deleteById(String id);
}
