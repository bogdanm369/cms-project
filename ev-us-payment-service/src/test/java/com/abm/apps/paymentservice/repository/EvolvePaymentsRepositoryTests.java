package com.abm.apps.paymentservice.repository;

import com.abm.apps.evcommons.beans.domain.payment.product.currencies.Currency;
import com.abm.apps.evcommons.beans.domain.payment.product.periods.SubscriptionPeriodType;
import com.abm.apps.paymentservice.utils.PaymentsServiceUtils;
import com.abm.apps.paymentservice.domain.EvolvePayment;
import com.abm.apps.paymentservice.domain.products.impl.evolve.EvolveSubscriptionProduct;
import com.abm.apps.paymentservice.domain.products.impl.generic.GenericPrice;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EvolvePaymentsRepositoryTests {

    @Autowired
    private EvolvePaymentsRepository evolvePaymentsRepository;

    @Autowired
    private EvolveSubscriptionProductsRepository evolveSubscriptionProductsRepository;

    @Autowired
    private PaymentsServiceUtils paymentsServiceUtils;

    @Test
    public void testFindPaymentByUniqueReference() {

        String uniqueReference = "reference-2";

        EvolveSubscriptionProduct evolveSubscriptionProduct = buildEvolveSubscriptionProduct();
        evolveSubscriptionProductsRepository.save(evolveSubscriptionProduct);

        EvolvePayment evolvePayment = buildEvolvePayment("reference-1", "uSeR-1", evolveSubscriptionProduct);
        evolvePaymentsRepository.save(evolvePayment);

        EvolvePayment evolvePayment2 = buildEvolvePayment(uniqueReference, "uSeR-2", evolveSubscriptionProduct);
        evolvePaymentsRepository.save(evolvePayment2);

        EvolvePayment  evolvePaymentResult = evolvePaymentsRepository.findOneByUniquePaymentReference(uniqueReference);


        assertEquals(uniqueReference, evolvePaymentResult.getUniquePaymentReference());
        assertNotNull(evolvePaymentResult);
    }

    @Test
    public void testFindAllPaymentsByUsername() {
        EvolveSubscriptionProduct evolveSubscriptionProduct = buildEvolveSubscriptionProduct();
        evolveSubscriptionProductsRepository.save(evolveSubscriptionProduct);

        EvolvePayment evolvePayment = buildEvolvePayment("uSeR-1", evolveSubscriptionProduct);
        evolvePaymentsRepository.save(evolvePayment);

        EvolvePayment evolvePayment2 = buildEvolvePayment("uSeR-2", evolveSubscriptionProduct);
        evolvePaymentsRepository.save(evolvePayment2);

        EvolvePayment evolvePayment3 = buildEvolvePayment("USeR-2", evolveSubscriptionProduct);
        evolvePaymentsRepository.save(evolvePayment3);

        List<EvolvePayment> evolvePayments = evolvePaymentsRepository.findAllByUsernameIgnoreCase("user-2");

        assertEquals(2, evolvePayments.size());
    }

    private EvolvePayment buildEvolvePayment(String paymentReference, String username, EvolveSubscriptionProduct evolveSubscriptionProduct) {

        EvolvePayment evolvePayment = EvolvePayment.builder().
                evolveSubscription(evolveSubscriptionProduct)
                .username(Strings.isEmpty(username) ? "default-username" : username)
                .uniquePaymentReference(Strings.isEmpty(paymentReference) ? paymentsServiceUtils.uniquePaymentReference() : paymentReference).build();

        return evolvePayment;
    }

    private EvolvePayment buildEvolvePayment(String username, EvolveSubscriptionProduct evolveSubscriptionProduct) {
        return buildEvolvePayment(null, username, evolveSubscriptionProduct);
    }


    private EvolvePayment buildEvolvePayment() {
        return buildEvolvePayment(null, buildEvolveSubscriptionProduct());
    }

    private EvolveSubscriptionProduct buildEvolveSubscriptionProduct() {

        EvolveSubscriptionProduct evolveSubscriptionProduct = EvolveSubscriptionProduct.builder()
                .productCode("subscription_product_code")
                .productName("6 month subscription product")
                .creationDate(new Date())
                .subscriptionPeriodType(SubscriptionPeriodType.SIX_MONTHS)
                .isActive(true)
                .productDescription("Evolve Application subscription for 6 months")
                .price(GenericPrice.builder().currency(Currency.EUR).value(new BigDecimal(120)).build()).build();

        return evolveSubscriptionProduct;
    }
}
