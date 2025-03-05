package com.pharmacy.management.infrastructure.rest;

import com.pharmacy.management.infrastructure.rest.models.req.CreateClientRequest;
import com.pharmacy.management.infrastructure.rest.models.req.SubscribeMedicationRequest;
import com.pharmacy.management.infrastructure.rest.models.req.UpdateClientRequest;
import com.pharmacy.management.infrastructure.rest.models.res.ClientResponse;
import com.pharmacy.management.infrastructure.rest.models.res.CreateClientResponse;
import com.pharmacy.management.infrastructure.rest.models.res.UpdateClientResponse;
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

@RequestMapping(value = "clients")
@Tag(name = "Clients")
public interface ClientRestApi {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new client.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was observed"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<CreateClientResponse> create(@RequestBody @Valid CreateClientRequest req);

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Retrieve a client by its identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Client was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> get(@PathVariable(name = "id") String id);

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "List all clients.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All clients retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    Set<ClientResponse> list();

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new client.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated successfully"),
            @ApiResponse(responseCode = "404", description = "Client was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<UpdateClientResponse> update(
            @PathVariable final String id,
            @RequestBody @Valid final UpdateClientRequest req);

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a client by its identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Client deleted successfully"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    void delete(@PathVariable(name = "id") final String id);

    @PostMapping(value = "/{id}/subscribe")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Subscribe a new medication.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Subscribed successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was observed"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    void subscribe(
            @PathVariable String id,
            @RequestBody @Valid SubscribeMedicationRequest req);
}
