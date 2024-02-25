package com.test.test.credit.service;
import com.test.test.client.Client;
import com.test.test.client.ClientRepository;
import com.test.test.credit.CreditDecision;
import com.test.test.credit.repository.CreditAgreementRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class CreditService {
    private ClientRepository clientRepository;
    private CreditAgreementRepository creditAgreementRepository;

    public CreditDecision makeCreditDecision(Client application, CreditDecision decision) {

        Random random = new Random();
        decision.setApproved(random.nextBoolean());
        if (decision.isApproved()) {
            decision.setTerm(random.nextInt(12) + 1);
            decision.setAmount(application.getRequestedCreditAmount());
        }
        return decision;
    }

}