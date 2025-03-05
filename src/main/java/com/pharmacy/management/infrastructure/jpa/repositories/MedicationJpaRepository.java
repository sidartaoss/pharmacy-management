package com.pharmacy.management.infrastructure.jpa.repositories;

import com.pharmacy.management.infrastructure.jpa.entities.MedicationEntity;
import org.springframework.data.repository.CrudRepository;

public interface MedicationJpaRepository extends CrudRepository<MedicationEntity, String> {
}
