package com.pharmacy.management.application.subscription;

import com.pharmacy.management.application.subscription.impl.DefaultListSubscription;
import com.pharmacy.management.domain.subscription.Subscription;
import com.pharmacy.management.domain.subscription.SubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListSubscriptionTest {

    private SubscriptionRepository subscriptionRepository;
    private DefaultListSubscription listSubscription;

    @BeforeEach
    void setUp() {
        subscriptionRepository = mock(SubscriptionRepository.class);
        listSubscription = new DefaultListSubscription(subscriptionRepository);
    }

    @Test
    void testListSubscriptionsSuccess() {
        // Arrange
        ListSubscription.Input input = mock(ListSubscription.Input.class);
        when(input.clientEmail()).thenReturn("john.doe@example.com");

        Subscription subscription1 = Subscription.newSubscription("1", "1", "John Doe", "john.doe@example.com", "1234567890", "101", "Medication A", "Brand A", new BigDecimal("500"), 15);
        Subscription subscription2 = Subscription.newSubscription("2", "1", "John Doe", "john.doe@example.com", "1234567890", "102", "Medication B", "Brand B", new BigDecimal("250"), 20);

        Set<Subscription> subscriptions = Set.of(subscription1, subscription2);
        when(subscriptionRepository.listBy("john.doe@example.com")).thenReturn(subscriptions);

        // Act
        Set<ListSubscription.Output> output = listSubscription.execute(input);

        // Assert
        assertNotNull(output);
        assertEquals(2, output.size());
    }

    @Test
    void testListSubscriptionsClientNotFound() {
        // Arrange
        ListSubscription.Input input = mock(ListSubscription.Input.class);
        when(input.clientEmail()).thenReturn("nonexistent@example.com");

        when(subscriptionRepository.listBy("nonexistent@example.com")).thenReturn(Set.of());

        // Act
        Set<ListSubscription.Output> output = listSubscription.execute(input);

        // Assert
        assertNotNull(output);
        assertTrue(output.isEmpty());
    }
}