package com.pharmacy.management.infrastructure.rest;

import com.pharmacy.management.infrastructure.rest.models.res.SubscriptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@RequestMapping(value = "subscriptions")
@Tag(name = "Subscriptions")
public interface SubscriptionRestApi {

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "List all subscriptions from the client, filtering by email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All subscriptions from the client retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    Set<SubscriptionResponse> listBy(@RequestParam("clientEmail") final String clientEmail);
}
