package com.pharmacy.management.domain.client;

import java.util.Optional;

public interface ClientMedicationRepository {

    String nextId();

    ClientMedication save(ClientMedication clientMedication);

    Optional<ClientMedication> findByClientIdAndMedicationId(String clientId, String medicationId);
}
