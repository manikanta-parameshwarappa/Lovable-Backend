package com.manikanta.projects.lovable_backend.service;

import com.manikanta.projects.lovable_backend.dto.subscription.CheckoutRequest;
import com.manikanta.projects.lovable_backend.dto.subscription.CheckoutResponse;
import com.manikanta.projects.lovable_backend.dto.subscription.PortalResponse;
import com.stripe.model.StripeObject;

import java.util.Map;

public interface PaymentProcessor {
    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request);
    PortalResponse openCustomerPortal();
    void handleWebhookEvent(String type, StripeObject stripeObject, Map<String, String> metadata);
}
