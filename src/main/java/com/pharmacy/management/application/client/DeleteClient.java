package com.pharmacy.management.application.client;

import com.pharmacy.management.application.UnitUseCase;

public abstract class DeleteClient
        extends UnitUseCase<DeleteClient.Input> {

    public interface Input {

        String id();
    }
}
