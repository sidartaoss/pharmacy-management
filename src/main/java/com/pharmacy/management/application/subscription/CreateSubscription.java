package com.pharmacy.management.application.subscription;

import com.pharmacy.management.application.UseCase;

public abstract class CreateSubscription extends
        UseCase<CreateSubscription.Input, CreateSubscription.Output> {

    public interface Input {

        String clientId();

        String medicationId();
    }

    public interface Output {
        String subscriptionId();
    }

}
