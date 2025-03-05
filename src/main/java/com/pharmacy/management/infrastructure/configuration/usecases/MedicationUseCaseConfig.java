package com.pharmacy.management.infrastructure.configuration.usecases;

import com.pharmacy.management.application.medication.*;
import com.pharmacy.management.application.medication.impl.*;
import com.pharmacy.management.domain.medication.MedicationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class MedicationUseCaseConfig {

    @Bean
    public CreateMedication createMedication(final MedicationRepository medicationRepository) {
        return new DefaultCreateMedication(medicationRepository);
    }

    @Bean
    public GetMedicationById getMedicationById(final MedicationRepository medicationRepository) {
        return new DefaultGetMedicationById(medicationRepository);
    }

    @Bean
    public ListMedication listMedication(final MedicationRepository medicationRepository) {
        return new DefaultListMedication(medicationRepository);
    }

    @Bean
    public UpdateMedication updateMedication(final MedicationRepository medicationRepository) {
        return new DefaultUpdateMedication(medicationRepository);
    }

    @Bean
    public DeleteMedication deleteMedication(final MedicationRepository medicationRepository) {
        return new DefaultDeleteMedication(medicationRepository);
    }
}
