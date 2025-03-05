package com.pharmacy.management.application.client;

import com.pharmacy.management.application.UseCase;

public abstract class UpdateClient
        extends UseCase<UpdateClient.Input, UpdateClient.Output> {

    public interface Input {

        String id();

        String name();

        String email();

        String cpf();

        String phoneNumber();
    }

    public interface Output {
        String clientId();
    }
}
