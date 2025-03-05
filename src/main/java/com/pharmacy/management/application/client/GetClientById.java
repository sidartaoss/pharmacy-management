package com.pharmacy.management.application.client;

import com.pharmacy.management.application.UseCase;

import java.util.Optional;

public abstract class GetClientById
        extends UseCase<GetClientById.Input, Optional<GetClientById.Output>> {

    public interface Input {

        String id();
    }

    public interface Output {

        String id();

        String name();

        String email();

        String cpf();

        String phoneNumber();
    }
}
