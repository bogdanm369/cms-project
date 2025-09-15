package com.abm.apps.paymentservice.repository;

import com.abm.apps.paymentservice.domain.products.impl.evolve.EvolveSubscriptionProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EvolveSubscriptionProductsRepository extends MongoRepository<EvolveSubscriptionProduct, String> {
    List<EvolveSubscriptionProduct> findAllByIsActive(boolean isActive);

    EvolveSubscriptionProduct findOneByProductCode(String productCode);
}
