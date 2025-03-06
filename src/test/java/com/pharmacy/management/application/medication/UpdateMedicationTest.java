package com.pharmacy.management.application.medication;

import com.pharmacy.management.application.medication.impl.DefaultUpdateMedication;
import com.pharmacy.management.domain.exceptions.NotFoundException;
import com.pharmacy.management.domain.medication.Medication;
import com.pharmacy.management.domain.medication.MedicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateMedicationTest {

    private MedicationRepository medicationRepository;
    private DefaultUpdateMedication updateMedication;

    @BeforeEach
    void setUp() {
        medicationRepository = mock(MedicationRepository.class);
        updateMedication = new DefaultUpdateMedication(medicationRepository);
    }

    @Test
    void testUpdateMedicationSuccess() {
        // Arrange
        UpdateMedication.Input input = mock(UpdateMedication.Input.class);
        when(input.id()).thenReturn("1");
        when(input.name()).thenReturn("Updated Medication");
        when(input.brand()).thenReturn("Updated Brand");
        when(input.price()).thenReturn(new BigDecimal("15.00"));
        when(input.dosage()).thenReturn(new BigDecimal("600"));

        Medication existingMedication = Medication.newMedication("1", "Medication A", "Brand A", new BigDecimal("10.00"), new BigDecimal("500"));
        when(medicationRepository.findById("1")).thenReturn(Optional.of(existingMedication));

        // Act
        UpdateMedication.Output output = updateMedication.execute(input);

        // Assert
        assertNotNull(output);
        assertEquals("1", output.medicationId());
        verify(medicationRepository, times(1)).save(any(Medication.class));
    }

    @Test
    void testUpdateNonExistentMedication() {
        // Arrange
        UpdateMedication.Input input = mock(UpdateMedication.Input.class);
        when(input.id()).thenReturn("non-existent-id");

        when(medicationRepository.findById("non-existent-id")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> updateMedication.execute(input));
    }

    @Test
    void testUpdateMedicationWithNullRepository() {
        // Arrange
        MedicationRepository nullRepository = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> new DefaultUpdateMedication(nullRepository));
    }
}