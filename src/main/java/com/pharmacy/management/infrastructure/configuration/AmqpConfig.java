package com.pharmacy.management.infrastructure.configuration;

import com.pharmacy.management.infrastructure.configuration.annotations.MedicationAttachedQueue;
import com.pharmacy.management.infrastructure.configuration.annotations.MedicationEvents;
import com.pharmacy.management.infrastructure.configuration.properties.amqp.QueueProperties;
import org.springframework.amqp.core.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    @Bean
    @ConfigurationProperties("amqp.queues.medication-attached")
    @MedicationAttachedQueue
    public QueueProperties medicationAttachedQueueProperties() {
        return new QueueProperties();
    }

    @Configuration
    static class Admin {

        @Bean
        @MedicationEvents
        public Exchange medicationEventsExchange(@MedicationAttachedQueue QueueProperties props) {
            return new DirectExchange(props.getExchange());
        }

        @Bean
        @MedicationAttachedQueue
        public Queue medicationAttachedQueue(@MedicationAttachedQueue QueueProperties props) {
            return new Queue(props.getQueue());
        }

        @Bean
        @MedicationAttachedQueue
        public Binding medicationAttachedQueueBinding(
                @MedicationEvents DirectExchange exchange,
                @MedicationAttachedQueue Queue queue,
                @MedicationAttachedQueue QueueProperties props
        ) {
            return BindingBuilder.bind(queue).to(exchange).with(props.getRoutingKey());
        }
    }
}
