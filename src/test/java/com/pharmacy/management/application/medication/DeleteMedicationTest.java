package com.pharmacy.management.application.medication;

import com.pharmacy.management.application.medication.impl.DefaultDeleteMedication;
import com.pharmacy.management.domain.medication.MedicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteMedicationTest {

    private MedicationRepository medicationRepository;
    private DefaultDeleteMedication deleteMedication;

    @BeforeEach
    void setUp() {
        medicationRepository = mock(MedicationRepository.class);
        deleteMedication = new DefaultDeleteMedication(medicationRepository);
    }

    @Test
    void testDeleteMedicationSuccess() {
        // Arrange
        DeleteMedication.Input input = mock(DeleteMedication.Input.class);
        when(input.id()).thenReturn("1");

        // Act
        deleteMedication.execute(input);

        // Assert
        verify(medicationRepository, times(1)).deleteById("1");
    }

    @Test
    void testDeleteMedicationWithNullRepository() {
        // Arrange
        MedicationRepository nullRepository = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> new DefaultDeleteMedication(nullRepository));
    }

    @Test
    void testDeleteNonExistentMedication() {
        // Arrange
        DeleteMedication.Input input = mock(DeleteMedication.Input.class);
        when(input.id()).thenReturn("non-existent-id");
        doNothing().when(medicationRepository).deleteById("non-existent-id");

        // Act & Assert
        assertDoesNotThrow(() -> deleteMedication.execute(input));
    }
}