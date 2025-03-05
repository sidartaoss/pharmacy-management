package com.pharmacy.management.application.client;

import com.pharmacy.management.application.UnitUseCase;

public abstract class AttachMedication
        extends UnitUseCase<AttachMedication.Input> {

    public interface Input {

        String clientId();

        String medicationId();

        Integer monthlyRenewalDay();
    }

}
