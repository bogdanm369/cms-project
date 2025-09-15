package com.abm.apps.paymentservice.repository;

import com.abm.apps.paymentservice.domain.EvolvePayment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EvolvePaymentsRepository extends MongoRepository<EvolvePayment, String> {
    EvolvePayment findOneByUniquePaymentReference(String uniquePaymentReference);

    List<EvolvePayment> findAllByUsernameIgnoreCase(String username);
}
