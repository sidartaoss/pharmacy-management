package com.pharmacy.management.infrastructure.amqp;

import com.pharmacy.management.application.subscription.CreateSubscription;
import com.pharmacy.management.infrastructure.configuration.json.Json;
import com.pharmacy.management.infrastructure.rest.models.res.MedicationAttachedResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MedicationAttachedListener {

    private static final Logger log = LoggerFactory.getLogger(MedicationAttachedListener.class);

    private static final String LISTENER_ID = "medicationAttachedListener";

    private final CreateSubscription createSubscription;

    public MedicationAttachedListener(final CreateSubscription createSubscription) {
        this.createSubscription = Objects.requireNonNull(createSubscription);
    }

    @RabbitListener(id = LISTENER_ID, queues = "${amqp.queues.medication-attached.queue}")
    public void onMedicationAttachedMessage(@Payload final String message) {
        final var aResult = Json.readValue(message, MedicationAttachedResult.class);
        log.info("[message:medication.attached] [payload:{}]", message);
        record Input(String clientId, String medicationId, Integer monthlyRenewalDay)
                implements CreateSubscription.Input {
        }
        this.createSubscription.execute(new Input(aResult.clientId(), aResult.medicationId(),
                aResult.monthlyRenewalDay()));
    }


}
