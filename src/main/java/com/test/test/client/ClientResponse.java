package com.test.test.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ClientResponse {
    private Long id;
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

    public static ClientResponse of(Client entity){
        return ClientResponse.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .middleName(entity.getMiddleName())
                .passport(entity.getPassport())
                .maritalStatus(entity.getMaritalStatus())
                .address(entity.getAddress())
                .phoneNumber(entity.getPhoneNumber())
                .employmentStatus(entity.getEmploymentStatus())
                .employmentPeriod(entity.getEmploymentPeriod())
                .employmentPosition(entity.getEmploymentPosition())
                .employmentOrganization(entity.getEmploymentOrganization())
                .requestedCreditAmount(entity.getRequestedCreditAmount())
                .build();
    }
}
