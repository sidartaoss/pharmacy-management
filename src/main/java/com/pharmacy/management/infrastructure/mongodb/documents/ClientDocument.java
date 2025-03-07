package com.pharmacy.management.infrastructure.mongodb.documents;

import com.pharmacy.management.domain.client.Client;
import com.pharmacy.management.domain.client.ClientMedication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Document(collection = "clients")
public class ClientDocument {

    @Id
    private String id;

    private String name;

    private String email;

    private String cpf;

    private String phoneNumber;

    private Set<ClientMedicationDocument> medications;

    public ClientDocument() {
        this.medications = HashSet.newHashSet(0);
    }

    private ClientDocument(String id, String name, String email, String cpf, String phoneNumber) {
        this();
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
    }

    public static ClientDocument of(final Client aClient) {
        final var aDocument = new ClientDocument(
                aClient.id(),
                aClient.name(),
                aClient.email(),
                aClient.cpf(),
                aClient.phoneNumber()
        );
        aClient.allMedications().forEach(aDocument::addMedication);
        return aDocument;
    }

    public Client toDomain() {
        return Client.with(
                this.id,
                this.name,
                this.email,
                this.cpf,
                this.phoneNumber,
                getMedications().stream()
                        .map(ClientMedicationDocument::toDomain)
                        .collect(Collectors.toSet())
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<ClientMedicationDocument> getMedications() {
        return medications;
    }

    public void setMedications(Set<ClientMedicationDocument> medications) {
        this.medications = medications;
    }

    private void addMedication(ClientMedication clientMedication) {
        getMedications().add(ClientMedicationDocument.of(clientMedication));
    }
}
