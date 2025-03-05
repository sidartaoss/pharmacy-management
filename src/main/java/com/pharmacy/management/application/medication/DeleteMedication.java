package com.pharmacy.management.application.medication;

import com.pharmacy.management.application.UnitUseCase;

public abstract class DeleteMedication
        extends UnitUseCase<DeleteMedication.Input> {

    public interface Input {

        String id();
    }
}
