package com.pharmacy.management.application.client;

import com.pharmacy.management.application.NullaryUseCase;

import java.util.Set;

public abstract class ListClient
        extends NullaryUseCase<Set<ListClient.Output>> {

    public interface Output {

        String id();

        String name();

        String email();

        String cpf();

        String phoneNumber();
    }
}
