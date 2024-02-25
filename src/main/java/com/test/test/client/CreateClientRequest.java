package com.test.test.client;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateClientRequest {
    private String firstName;
    private String lastName;
    private String middleName;
    private String passport;
    private String maritalStatus;
    private String address;
    private String phoneNumber;
    private String employmentStatus;
    private String employmentPeriod;
    private String employmentPosition;
    private String employmentOrganization;
    private BigDecimal requestedCreditAmount;
}
