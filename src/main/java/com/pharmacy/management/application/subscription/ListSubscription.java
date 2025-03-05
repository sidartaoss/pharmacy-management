package com.pharmacy.management.application.subscription;

import com.pharmacy.management.application.UseCase;

import java.math.BigDecimal;
import java.util.Set;

public abstract class ListSubscription
        extends UseCase<ListSubscription.Input, Set<ListSubscription.Output>> {

    public interface Input {
        String clientEmail();
    }

    public interface Output {

        String id();

        String clientId();

        String clientName();

        String clientEmail();

        String clientPhoneNumber();

        String medicationId();

        String medicationName();

        String medicationBrand();

        BigDecimal medicationDosage();

        Integer medicationMonthlyRenewalDay();
    }
}