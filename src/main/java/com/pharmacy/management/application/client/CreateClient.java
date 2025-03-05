package com.pharmacy.management.application.client;

import com.pharmacy.management.application.UseCase;

public abstract class CreateClient
        extends UseCase<CreateClient.Input, CreateClient.Output> {

    public interface Input {

        String name();

        String email();

        String cpf();

        String phoneNumber();
    }

    public interface Output {
        String clientId();
    }
}
