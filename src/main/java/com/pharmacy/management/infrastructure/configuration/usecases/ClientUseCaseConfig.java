package com.pharmacy.management.infrastructure.configuration.usecases;

import com.pharmacy.management.application.client.*;
import com.pharmacy.management.application.client.impl.*;
import com.pharmacy.management.domain.client.ClientRepository;
import com.pharmacy.management.domain.medication.MedicationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ClientUseCaseConfig {

    @Bean
    public CreateClient createClient(final ClientRepository clientRepository) {
        return new DefaultCreateClient(clientRepository);
    }

    @Bean
    public GetClientById getClientById(final ClientRepository clientRepository) {
        return new DefaultGetClientById(clientRepository);
    }

    @Bean
    public ListClient listClient(final ClientRepository clientRepository) {
        return new DefaultListClient(clientRepository);
    }

    @Bean
    public UpdateClient updateClient(final ClientRepository clientRepository) {
        return new DefaultUpdateClient(clientRepository);
    }

    @Bean
    public DeleteClient deleteClient(final ClientRepository clientRepository) {
        return new DefaultDeleteClient(clientRepository);
    }

    @Bean
    public AttachMedication attachMedication(final MedicationRepository medicationRepository, final ClientRepository clientRepository) {
        return new DefaultAttachMedication(medicationRepository, clientRepository);
    }
}
