package com.pharmacy.management.infrastructure.configuration.usecases;

import com.pharmacy.management.application.client.CreateClientMedication;
import com.pharmacy.management.application.client.impl.DefaultCreateClientMedication;
import com.pharmacy.management.domain.client.ClientMedicationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ClientMedicationUseCaseConfig {

    @Bean
    public CreateClientMedication createClientMedication(final ClientMedicationRepository clientMedicationRepository) {
        return new DefaultCreateClientMedication(clientMedicationRepository);
    }
}
