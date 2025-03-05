package com.pharmacy.management.infrastructure.rest;

import com.pharmacy.management.infrastructure.rest.models.req.CreateMedicationRequest;
import com.pharmacy.management.infrastructure.rest.models.req.UpdateMedicationRequest;
import com.pharmacy.management.infrastructure.rest.models.res.CreateMedicationResponse;
import com.pharmacy.management.infrastructure.rest.models.res.MedicationResponse;
import com.pharmacy.management.infrastructure.rest.models.res.UpdateMedicationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping(value = "medications")
@Tag(name = "Medications")
public interface MedicationRestApi {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new medication.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was observed"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<CreateMedicationResponse> create(@RequestBody @Valid CreateMedicationRequest req);

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Retrieve a medication by its identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medication retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Medication was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> get(@PathVariable(name = "id") String id);

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "List all medications.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All medications retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    Set<MedicationResponse> list();

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a medication.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated successfully"),
            @ApiResponse(responseCode = "404", description = "Medication was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<UpdateMedicationResponse> update(
            @PathVariable final String id,
            @RequestBody @Valid final UpdateMedicationRequest req);

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a medication by its identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Medication deleted successfully"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    void delete(@PathVariable(name = "id") final String id);
}