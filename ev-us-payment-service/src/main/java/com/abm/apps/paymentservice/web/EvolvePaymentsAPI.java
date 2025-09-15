package com.abm.apps.paymentservice.web;

import com.abm.apps.paymentservice.domain.products.impl.evolve.EvolveSubscriptionProduct;
import com.abm.apps.paymentservice.service.EvolvePaymentsService;
import com.abm.apps.paymentservice.web.domain.PaymentRequestDTO;
import com.abm.apps.paymentservice.web.domain.SubscriptionCheckRequestDTO;
import com.abm.apps.tools.flowtracer.logging.MethodLogging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EvolvePaymentsAPI {


    @Autowired
    private EvolvePaymentsService evolvePaymentsService;

    /**
     * Returns a  list with all the Active EvolveSubscriptionProducts
     *
     * @return
     */
    @MethodLogging
    @GetMapping("/productsCatalog")
    public ResponseEntity<?> getEvolveProductsCatalog() {

        List<EvolveSubscriptionProduct> evolveSubscriptionProducts = evolvePaymentsService.getAvailableProducts();

        if (CollectionUtils.isEmpty(evolveSubscriptionProducts)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(evolveSubscriptionProducts);
    }

    /**
     * Endpoint used to make a Payment
     *
     * @param paymentRequestDTO
     * @return
     */
    @PostMapping("/makePayment")
    public ResponseEntity<?> makePayment(@RequestBody PaymentRequestDTO paymentRequestDTO) {


        return null;
    }

    @GetMapping("/getPaymentDetails/{uniquePaymentReference}")
    public ResponseEntity<?> getPaymentDetails(@PathVariable String uniquePaymentReference) {

        return null;
    }

    @GetMapping("/getPaymentsForUsername/{username}")
    public ResponseEntity<?> getPaymentsForUsername(@PathVariable String username) {

        return null;
    }

    /**
     * Endpoint used to check if a subscription is Active for the Specified Username at an (optional) Date - if no date is supplied, it will be checked for the current Date
     *
     * @param subscriptionCheckRequestDTO
     * @return
     */
    @GetMapping("/checkSubscriptionForUser/{username}")
    public ResponseEntity<?> checkActiveSubscriptionForUser(@RequestBody SubscriptionCheckRequestDTO subscriptionCheckRequestDTO) {

        return null;
    }

    @ResponseBody
    @GetMapping("/echo")
    public String echoText() {
        return "Hello !";
    }
}
