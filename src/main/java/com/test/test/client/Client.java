package com.test.test.client;

import com.test.test.credit.CreditAgreement;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<CreditAgreement> creditAgreements;

}

