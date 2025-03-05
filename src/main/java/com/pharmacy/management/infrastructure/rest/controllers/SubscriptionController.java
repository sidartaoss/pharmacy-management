package com.pharmacy.management.infrastructure.rest.controllers;

import com.pharmacy.management.application.subscription.ListSubscription;
import com.pharmacy.management.domain.utils.CollectionUtils;
import com.pharmacy.management.infrastructure.rest.SubscriptionRestApi;
import com.pharmacy.management.infrastructure.rest.models.res.SubscriptionResponse;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Set;

@RestController
public class SubscriptionController implements SubscriptionRestApi {

    private final ListSubscription listSubscription;

    public SubscriptionController(final ListSubscription listSubscription) {
        this.listSubscription = Objects.requireNonNull(listSubscription);
    }

    @Override
    public Set<SubscriptionResponse> listBy(final String clientEmail) {
        record Input(String clientEmail) implements ListSubscription.Input {
        }
        final var out = this.listSubscription.execute(new Input(clientEmail));
        return CollectionUtils.map(out, SubscriptionResponse::new);
    }
}