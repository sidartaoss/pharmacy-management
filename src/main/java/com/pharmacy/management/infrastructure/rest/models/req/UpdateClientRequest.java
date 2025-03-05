package com.pharmacy.management.infrastructure.rest.models.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateClientRequest(
        @NotBlank @Size(max = 255) String name,
        @NotBlank @Size(max = 254) String email,
        @NotBlank @Size(max = 14) String cpf,
        @NotBlank @Size(max = 15) String phoneNumber
) {
}
