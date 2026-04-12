package com.manikanta.projects.lovable_backend.service.impl;

import com.manikanta.projects.lovable_backend.dto.subscription.CheckoutRequest;
import com.manikanta.projects.lovable_backend.dto.subscription.CheckoutResponse;
import com.manikanta.projects.lovable_backend.dto.subscription.PortalResponse;
import com.manikanta.projects.lovable_backend.service.PaymentProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StripePaymentProcessor implements PaymentProcessor {
    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request) {
        // the goal of this particular function is as soon as the user the btn ,
        // we are going to redirect the user to another URL where they can pay via stripe
        return null;
    }

    @Override
    public PortalResponse openCustomerPortal() {
        return null;
    }
}
