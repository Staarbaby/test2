package com.test.test.credit;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Random;

@Getter
@Setter
public class CreditDecision {
    private boolean approved;
    private int term;
    private BigDecimal amount;

    public CreditDecision() {
        Random random = new Random();
        this.approved = random.nextBoolean();
        this.term = random.nextInt(12) + 1;
        this.amount = new BigDecimal(random.nextInt(100000) + 1);
    }
}
