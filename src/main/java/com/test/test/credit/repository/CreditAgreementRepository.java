package com.test.test.credit.repository;

import com.test.test.credit.CreditAgreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditAgreementRepository extends JpaRepository<CreditAgreement, Long> {
}
