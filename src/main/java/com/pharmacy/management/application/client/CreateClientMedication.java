package com.pharmacy.management.application.client;

import com.pharmacy.management.application.UseCase;

public abstract class CreateClientMedication
        extends UseCase<CreateClientMedication.Input, CreateClientMedication.Output> {

    public interface Input {

        String clientId();

        String medicationId();

        Integer monthlyRenewalDay();
    }

    public interface Output {
        String clientMedicationId();
    }
}
