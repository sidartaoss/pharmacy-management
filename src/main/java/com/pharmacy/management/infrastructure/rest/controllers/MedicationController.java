package com.pharmacy.management.infrastructure.rest.controllers;

import com.pharmacy.management.application.medication.*;
import com.pharmacy.management.domain.utils.CollectionUtils;
import com.pharmacy.management.infrastructure.rest.MedicationRestApi;
import com.pharmacy.management.infrastructure.rest.models.req.CreateMedicationRequest;
import com.pharmacy.management.infrastructure.rest.models.req.UpdateMedicationRequest;
import com.pharmacy.management.infrastructure.rest.models.res.CreateMedicationResponse;
import com.pharmacy.management.infrastructure.rest.models.res.MedicationResponse;
import com.pharmacy.management.infrastructure.rest.models.res.UpdateMedicationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Objects;
import java.util.Set;

@RestController
public class MedicationController implements MedicationRestApi {

    private final CreateMedication createMedication;
    private final GetMedicationById getMedicationById;
    private final ListMedication listMedication;
    private final UpdateMedication updateMedication;
    private final DeleteMedication deleteMedication;

    public MedicationController(
            final CreateMedication createMedication,
            final GetMedicationById getMedicationById,
            final ListMedication listMedication,
            final UpdateMedication updateMedication,
            final DeleteMedication deleteMedication) {
        this.createMedication = Objects.requireNonNull(createMedication);
        this.getMedicationById = Objects.requireNonNull(getMedicationById);
        this.listMedication = Objects.requireNonNull(listMedication);
        this.updateMedication = Objects.requireNonNull(updateMedication);
        this.deleteMedication = Objects.requireNonNull(deleteMedication);
    }

    @Override
    public ResponseEntity<CreateMedicationResponse> create(final CreateMedicationRequest req) {
        record Input(String name, String brand, BigDecimal price, BigDecimal dosage)
                implements CreateMedication.Input {
        }
        final var out = this.createMedication.execute(new Input(req.name(), req.brand(), req.price(), req.dosage()));
        return ResponseEntity.created(URI.create("/medications/%s".formatted(out.medicationId()))).body(
                new CreateMedicationResponse(out.medicationId()));
    }

    @Override
    public ResponseEntity<?> get(final String id) {
        record Input(String id) implements GetMedicationById.Input {
        }
        return this.getMedicationById.execute(new Input(id))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @Override
    public Set<MedicationResponse> list() {
        final var out = this.listMedication.execute();
        return CollectionUtils.map(out, MedicationResponse::new);
    }

    @Override
    public ResponseEntity<UpdateMedicationResponse> update(final String id, final UpdateMedicationRequest req) {
        record Input(String id, String name, String brand, BigDecimal price, BigDecimal dosage)
                implements UpdateMedication.Input {
        }
        final var out = this.updateMedication.execute(new Input(id, req.name(), req.brand(), req.price(), req.dosage()));
        return ResponseEntity.accepted().body(new UpdateMedicationResponse(out.medicationId()));
    }

    @Override
    public void delete(final String id) {
        record Input(String id) implements DeleteMedication.Input {
        }
        this.deleteMedication.execute(new Input(id));
    }
}