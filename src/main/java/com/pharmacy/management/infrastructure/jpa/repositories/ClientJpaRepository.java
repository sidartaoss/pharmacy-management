package com.pharmacy.management.infrastructure.jpa.repositories;

import com.pharmacy.management.infrastructure.jpa.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientJpaRepository extends CrudRepository<ClientEntity, String> {
}
