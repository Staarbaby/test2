package com.test.test.credit;

import com.test.test.client.Client;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@DynamicInsert
@DynamicUpdate
@Table(name = "creditAgreement")
public class CreditAgreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_credit;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id", nullable = false)
    private Client client;
    private LocalDate signingDate;
    private BigDecimal creditAmount;
    private String signatureStatus;
}
