package com.pharmacy.management.domain.client;

import java.util.Optional;
import java.util.Set;

public interface ClientRepository {

    String nextId();

    Client save(Client cliente);

    Optional<Client> findById(String id);

    Set<Client> findAll();

    void deleteById(String id);
}
