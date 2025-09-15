package com.abm.apps.paymentservice.service;

import com.abm.apps.evcommons.utils.EvExceptionHelper;
import com.abm.apps.paymentservice.domain.EvolvePayment;
import com.abm.apps.paymentservice.domain.products.impl.evolve.EvolveSubscriptionProduct;
import com.abm.apps.paymentservice.http.client.UserProfileServiceClient;
import com.abm.apps.paymentservice.repository.EvolvePaymentsRepository;
import com.abm.apps.paymentservice.repository.EvolveSubscriptionProductsRepository;
import com.abm.apps.paymentservice.web.domain.userprofile.UserProfileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class EvolvePaymentsService {


    @Autowired
    private
    EvolvePaymentsRepository evolvePaymentsRepository;

    @Autowired
    private
    EvolveSubscriptionProductsRepository evolveSubscriptionProductsRepository;

    @Autowired
    private UserProfileServiceClient userProfileServiceClient;

    @Autowired
    private EvExceptionHelper evExceptionHelper;

    public EvolvePayment makePayment(String username, String productCode) {


        UserProfileDTO userProfileDTO = userProfileServiceClient.getUserProfile(username);

        if (userProfileDTO == null) {
            evExceptionHelper.throwValidationException(String.format("User Profile not found for profile [%s]", username));
        }

        EvolveSubscriptionProduct evolveSubscriptionProduct = evolveSubscriptionProductsRepository.findOneByProductCode(productCode);

        if (evolveSubscriptionProduct == null) {
            evExceptionHelper.throwValidationException(String.format("Product/subscription not found for code [%s]", productCode));
        }


        return null;
    }

    public EvolvePayment getPaymentByReference(String uniquePaymentReference) {

        EvolvePayment evolvePayment = evolvePaymentsRepository.findOneByUniquePaymentReference(uniquePaymentReference);
        log.debug("Found EvolvePayment for reference [{}] ? [{}]", uniquePaymentReference, evolvePayment != null);

        return evolvePayment;
    }

    public List<EvolvePayment> getAllPaymentsForUsername(String username) {

        List<EvolvePayment> evolvePayments = evolvePaymentsRepository.findAllByUsernameIgnoreCase(username);
        log.debug("Found [{}] EvolvePayments for Username [{}]", evolvePayments.size(), username);

        return evolvePayments;
    }

    public List<EvolveSubscriptionProduct> getAvailableProducts() {

        return evolveSubscriptionProductsRepository.findAllByIsActive(true);
    }

}
