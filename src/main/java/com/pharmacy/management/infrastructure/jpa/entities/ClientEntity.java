package com.pharmacy.management.infrastructure.jpa.entities;

import com.pharmacy.management.domain.client.Client;
import com.pharmacy.management.domain.client.ClientMedication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "clients")
public class ClientEntity {

    @Id
    private String id;

    private String name;

    private String email;

    private String cpf;

    private String phoneNumber;

    private Set<ClientMedicationEntity> medications;

    public ClientEntity() {
        this.medications = HashSet.newHashSet(0);
    }

    private ClientEntity(String id, String name, String email, String cpf, String phoneNumber) {
        this();
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
    }

    public static ClientEntity of(final Client aClient) {
        return new ClientEntity(
                aClient.id(),
                aClient.name(),
                aClient.email(),
                aClient.cpf(),
                aClient.phoneNumber()
        );
    }

    public Client toDomain() {
        return Client.newClient(
                this.id,
                this.name,
                this.email,
                this.cpf,
                this.phoneNumber
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

    public Set<ClientMedicationEntity> getMedications() {
        return medications;
    }

    public void setMedications(Set<ClientMedicationEntity> medications) {
        this.medications = medications;
    }

    private void addMedication(ClientMedication clientMedication) {
        getMedications().add(ClientMedicationEntity.of(this, clientMedication));
    }
}
