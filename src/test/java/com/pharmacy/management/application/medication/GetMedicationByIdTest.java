package com.pharmacy.management.application.medication;

import com.pharmacy.management.application.medication.impl.DefaultGetMedicationById;
import com.pharmacy.management.domain.medication.Medication;
import com.pharmacy.management.domain.medication.MedicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetMedicationByIdTest {

    private MedicationRepository medicationRepository;
    private DefaultGetMedicationById getMedicationById;

    @BeforeEach
    void setUp() {
        medicationRepository = mock(MedicationRepository.class);
        getMedicationById = new DefaultGetMedicationById(medicationRepository);
    }

    @Test
    void testGetMedicationByIdSuccess() {
        // Arrange
        GetMedicationById.Input input = mock(GetMedicationById.Input.class);
        when(input.id()).thenReturn("1");

        Medication medication = Medication.newMedication("1", "Medication A", "Brand A", new BigDecimal("10.00"), new BigDecimal("500"));
        when(medicationRepository.findById("1")).thenReturn(Optional.of(medication));

        // Act
        Optional<GetMedicationById.Output> output = getMedicationById.execute(input);

        // Assert
        assertTrue(output.isPresent());
        assertEquals("1", output.get().id());
        assertEquals("Medication A", output.get().name());
        assertEquals("Brand A", output.get().brand());
        assertEquals(new BigDecimal("10.00"), output.get().price());
        assertEquals(new BigDecimal("500"), output.get().dosage());
    }

    @Test
    void testGetMedicationByIdNotFound() {
        // Arrange
        GetMedicationById.Input input = mock(GetMedicationById.Input.class);
        when(input.id()).thenReturn("non-existent-id");

        when(medicationRepository.findById("non-existent-id")).thenReturn(Optional.empty());

        // Act
        Optional<GetMedicationById.Output> output = getMedicationById.execute(input);

        // Assert
        assertFalse(output.isPresent());
    }

    @Test
    void testGetMedicationByIdWithNullRepository() {
        // Arrange
        MedicationRepository nullRepository = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> new DefaultGetMedicationById(nullRepository));
    }
}