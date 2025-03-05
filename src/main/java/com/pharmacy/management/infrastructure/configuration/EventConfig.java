package com.pharmacy.management.infrastructure.configuration;

import com.pharmacy.management.infrastructure.configuration.annotations.MedicationAttachedQueue;
import com.pharmacy.management.infrastructure.configuration.properties.amqp.QueueProperties;
import com.pharmacy.management.infrastructure.services.EventService;
import com.pharmacy.management.infrastructure.services.impl.RabbitEventService;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfig {

    @Bean
    @MedicationAttachedQueue
    public EventService medicationAttachedEventService(
            @MedicationAttachedQueue final QueueProperties props,
            final RabbitOperations ops
    ) {
        return new RabbitEventService(props.getExchange(), props.getRoutingKey(), ops);
    }
}
