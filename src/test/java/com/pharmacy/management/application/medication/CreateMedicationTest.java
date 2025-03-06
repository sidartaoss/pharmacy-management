package com.pharmacy.management.application.medication;

import com.pharmacy.management.application.medication.impl.DefaultCreateMedication;
import com.pharmacy.management.domain.medication.Medication;
import com.pharmacy.management.domain.medication.MedicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateMedicationTest {

    private MedicationRepository medicationRepository;
    private DefaultCreateMedication createMedication;

    @BeforeEach
    void setUp() {
        medicationRepository = mock(MedicationRepository.class);
        createMedication = new DefaultCreateMedication(medicationRepository);
    }

    @Test
    void testCreateMedicationSuccess() {
        // Arrange
        CreateMedication.Input input = mock(CreateMedication.Input.class);
        when(input.name()).thenReturn("Medication A");
        when(input.brand()).thenReturn("Brand A");
        when(input.price()).thenReturn(new BigDecimal("10.00"));
        when(input.dosage()).thenReturn(new BigDecimal("500"));

        Medication medication = Medication.newMedication("1", "Medication A", "Brand A", new BigDecimal("10.00"), new BigDecimal("500"));
        when(medicationRepository.save(any(Medication.class))).thenReturn(medication);

        // Act
        CreateMedication.Output output = createMedication.execute(input);

        // Assert
        assertNotNull(output);
        assertEquals("1", output.medicationId());

        ArgumentCaptor<Medication> medicationCaptor = ArgumentCaptor.forClass(Medication.class);
        verify(medicationRepository).save(medicationCaptor.capture());
        Medication savedMedication = medicationCaptor.getValue();
        assertEquals("Medication A", savedMedication.name());
        assertEquals("Brand A", savedMedication.brand());
        assertEquals(new BigDecimal("10.00"), savedMedication.price());
        assertEquals(new BigDecimal("500"), savedMedication.dosage());
    }

    @Test
    void testCreateMedicationWithNullRepository() {
        // Arrange
        MedicationRepository nullRepository = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> new DefaultCreateMedication(nullRepository));
    }
}