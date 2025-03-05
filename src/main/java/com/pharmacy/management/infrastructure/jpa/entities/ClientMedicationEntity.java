package com.pharmacy.management.infrastructure.jpa.entities;

import com.pharmacy.management.domain.client.ClientMedication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clients_medications")
public class ClientMedicationEntity {

    @Id
    private String id;

    private String clientId;

    private String medicationId;

    private Integer monthlyRenewalDay;

    private ClientEntity client;

    public ClientMedicationEntity() {
    }

    private ClientMedicationEntity(
            final String id,
            final String clientId,
            final String medicationId,
            final Integer monthlyRenewalDay,
            final ClientEntity client
    ) {
        this.id = id;
        this.clientId = clientId;
        this.medicationId = medicationId;
        this.monthlyRenewalDay = monthlyRenewalDay;
        this.client = client;
    }

    public static ClientMedicationEntity of(
            final ClientEntity aClient, final ClientMedication aClientMedication) {
        return new ClientMedicationEntity(
                aClientMedication.id(),
                aClientMedication.clientId(),
                aClientMedication.medicationId(),
                aClientMedication.monthlyRenewalDay(),
                aClient
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }

    public Integer getMonthlyRenewalDay() {
        return monthlyRenewalDay;
    }

    public void setMonthlyRenewalDay(Integer monthlyRenewalDay) {
        this.monthlyRenewalDay = monthlyRenewalDay;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }
}
