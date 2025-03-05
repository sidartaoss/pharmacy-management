package com.pharmacy.management.domain.client;

public class ClientMedication {

    private String id;
    private String clientId;
    private String medicationId;
    private Integer monthlyRenewalDay;

    private ClientMedication(
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

    public static ClientMedication newClientMedication(
            final String clientId,
            final String medicationId,
            final Integer monthlyRenewalDay) {
        return new ClientMedication(null, clientId, medicationId, monthlyRenewalDay);
    }

    public String id() {
        return id;
    }

    public String clientId() {
        return clientId;
    }

    public String medicationId() {
        return medicationId;
    }

    public Integer monthlyRenewalDay() {
        return monthlyRenewalDay;
    }
}
