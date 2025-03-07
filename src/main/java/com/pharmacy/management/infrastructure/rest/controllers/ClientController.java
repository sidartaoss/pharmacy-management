package com.pharmacy.management.infrastructure.rest.controllers;

import com.pharmacy.management.application.client.*;
import com.pharmacy.management.domain.utils.CollectionUtils;
import com.pharmacy.management.infrastructure.mediator.AttachMediator;
import com.pharmacy.management.infrastructure.rest.ClientRestApi;
import com.pharmacy.management.infrastructure.rest.models.req.CreateClientRequest;
import com.pharmacy.management.infrastructure.rest.models.req.SubscribeMedicationRequest;
import com.pharmacy.management.infrastructure.rest.models.req.UpdateClientRequest;
import com.pharmacy.management.infrastructure.rest.models.res.ClientResponse;
import com.pharmacy.management.infrastructure.rest.models.res.CreateClientResponse;
import com.pharmacy.management.infrastructure.rest.models.res.UpdateClientResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.Set;

@RestController
public class ClientController implements ClientRestApi {

    private final CreateClient createClient;
    private final GetClientById getClientById;
    private final ListClient listClient;
    private final UpdateClient updateClient;
    private final DeleteClient deleteClient;
    private final AttachMediator attachMediator;

    public ClientController(
            final CreateClient createClient,
            final GetClientById getClientById,
            final ListClient listClient,
            final UpdateClient updateClient,
            final DeleteClient deleteClient,
            final AttachMediator attachMediator) {
        this.createClient = Objects.requireNonNull(createClient);
        this.getClientById = Objects.requireNonNull(getClientById);
        this.listClient = Objects.requireNonNull(listClient);
        this.updateClient = Objects.requireNonNull(updateClient);
        this.deleteClient = Objects.requireNonNull(deleteClient);
        this.attachMediator = Objects.requireNonNull(attachMediator);
    }

    @Override
    public ResponseEntity<CreateClientResponse> create(final CreateClientRequest req) {
        record Input(String name, String email, String cpf, String phoneNumber) implements CreateClient.Input {
        }
        final var out = this.createClient.execute(new Input(req.name(), req.email(), req.cpf(), req.phoneNumber()));
        return ResponseEntity.created(URI.create("/clients/%s".formatted(out.clientId()))).body(
                new CreateClientResponse(out.clientId()));
    }

    @Override
    public ResponseEntity<?> get(final String anId) {
        record Input(String id) implements GetClientById.Input {
        }
        return this.getClientById.execute(new Input(anId))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @Override
    public Set<ClientResponse> list() {
        final var out = this.listClient.execute();
        return CollectionUtils.map(out, ClientResponse::new);
    }

    @Override
    public ResponseEntity<UpdateClientResponse> update(final String id, final UpdateClientRequest req) {
        record Input(String id, String name, String email, String cpf,
                     String phoneNumber) implements UpdateClient.Input {
        }
        final var out = this.updateClient.execute(new Input(id, req.name(), req.email(), req.cpf(), req.phoneNumber()));
        return ResponseEntity.accepted().body(new UpdateClientResponse(out.clientId()));
    }

    @Override
    public void delete(final String id) {
        record Input(String id) implements DeleteClient.Input {
        }
        this.deleteClient.execute(new Input(id));
    }

    @Override
    public void subscribe(final String id, final SubscribeMedicationRequest req) {
        record Input(String clientId, String medicationId,
                     Integer monthlyRenewalDay) implements AttachMedication.Input {
        }
        this.attachMediator.attachMedication(new Input(id, req.medicationId(), req.monthlyRenewalDay()));
    }
}
