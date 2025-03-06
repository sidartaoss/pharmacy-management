package com.pharmacy.management.application.medication;

import com.pharmacy.management.application.medication.impl.DefaultListMedication;
import com.pharmacy.management.domain.medication.Medication;
import com.pharmacy.management.domain.medication.MedicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListMedicationTest {

    private MedicationRepository medicationRepository;
    private DefaultListMedication listMedication;

    @BeforeEach
    void setUp() {
        medicationRepository = mock(MedicationRepository.class);
        listMedication = new DefaultListMedication(medicationRepository);
    }

    @Test
    void testListMedicationsSuccess() {
        // Arrange
        Medication medication1 = Medication.newMedication("1", "Medication A", "Brand A", new BigDecimal("10.00"), new BigDecimal("500"));
        Medication medication2 = Medication.newMedication("2", "Medication B", "Brand B", new BigDecimal("20.00"), new BigDecimal("250"));
        Set<Medication> medications = Set.of(medication1, medication2);
        when(medicationRepository.findAll()).thenReturn(medications);

        // Act
        Set<ListMedication.Output> output = listMedication.execute();

        // Assert
        assertNotNull(output);
        assertEquals(2, output.size());

        assertTrue(output.stream().anyMatch(o ->
                o.id().equals("1") &&
                        o.name().equals("Medication A") &&
                        o.brand().equals("Brand A") &&
                        o.price().equals(new BigDecimal("10.00")) &&
                        o.dosage().equals(new BigDecimal("500"))
        ));

        assertTrue(output.stream().anyMatch(o ->
                o.id().equals("2") &&
                        o.name().equals("Medication B") &&
                        o.brand().equals("Brand B") &&
                        o.price().equals(new BigDecimal("20.00")) &&
                        o.dosage().equals(new BigDecimal("250"))
        ));
    }

    @Test
    void testListMedicationsEmpty() {
        // Arrange
        when(medicationRepository.findAll()).thenReturn(Set.of());

        // Act
        Set<ListMedication.Output> output = listMedication.execute();

        // Assert
        assertNotNull(output);
        assertTrue(output.isEmpty());
    }

    @Test
    void testListMedicationsWithNullRepository() {
        // Arrange
        MedicationRepository nullRepository = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> new DefaultListMedication(nullRepository));
    }
}