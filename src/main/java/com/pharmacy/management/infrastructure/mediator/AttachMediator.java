package com.pharmacy.management.infrastructure.mediator;

import com.pharmacy.management.application.client.AttachMedication;
import com.pharmacy.management.application.client.CreateClientMedication;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Function;

@Component
public class AttachMediator {

    private final CreateClientMedication createClientMedication;
    private final AttachMedication attachMedication;

    public AttachMediator(
            final CreateClientMedication createClientMedication,
            final AttachMedication attachMedication
    ) {
        this.createClientMedication = Objects.requireNonNull(createClientMedication);
        this.attachMedication = Objects.requireNonNull(attachMedication);
    }

    public void attachMedication(final AttachMedication.Input input) {
        createClientMedication().andThen(attachMedication()).apply(input);
    }

    private Function<AttachMedication.Input, AttachMedication.Input> createClientMedication() {
        record Input(String clientId, String medicationId, Integer monthlyRenewalDay)
                implements CreateClientMedication.Input {
        }
        return input -> {
            this.createClientMedication.execute(new Input(
                    input.clientId(),
                    input.medicationId(),
                    input.monthlyRenewalDay()
            ));
            return input;
        };
    }

    private Function<AttachMedication.Input, Void> attachMedication() {
        return input -> {
            attachMedication.execute(input);
            return null;
        };
    }
}