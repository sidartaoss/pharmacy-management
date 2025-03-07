package com.pharmacy.management.infrastructure.mongodb.documents;

import com.pharmacy.management.domain.client.ClientMedication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clients_medications")
public class ClientMedicationDocument {

    @Id
    private String id;

    private String clientId;

    private String medicationId;

    private Integer monthlyRenewalDay;

    public ClientMedicationDocument() {
    }

    private ClientMedicationDocument(
            final String id,
            final String clientId,
            final String medicationId,
            final Integer monthlyRenewalDay
    ) {
        this.id = id;
        this.clientId = clientId;
        this.medicationId = medicationId;
        this.monthlyRenewalDay = monthlyRenewalDay;
    }

    public static ClientMedicationDocument newClientMedicationDocument(
            final String id,
            final String clientId,
            final String medicationId,
            final Integer monthlyRenewalDay
    ) {
        return new ClientMedicationDocument(id, clientId, medicationId, monthlyRenewalDay);
    }

    public ClientMedication toDomain() {
        return ClientMedication.newClientMedication(
                getId(),
                getClientId(),
                getMedicationId(),
                getMonthlyRenewalDay()
        );
    }

    public static ClientMedicationDocument of(final ClientMedication aClientMedication) {
        return new ClientMedicationDocument(
                aClientMedication.id(),
                aClientMedication.clientId(),
                aClientMedication.medicationId(),
                aClientMedication.monthlyRenewalDay()
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
}
