package com.manikanta.projects.lovable_backend.service.impl;

import com.manikanta.projects.lovable_backend.dto.subscription.CheckoutRequest;
import com.manikanta.projects.lovable_backend.dto.subscription.CheckoutResponse;
import com.manikanta.projects.lovable_backend.dto.subscription.PortalResponse;
import com.manikanta.projects.lovable_backend.entity.Plan;
import com.manikanta.projects.lovable_backend.entity.User;
import com.manikanta.projects.lovable_backend.error.ResourceNotFoundException;
import com.manikanta.projects.lovable_backend.repository.PlanRepository;
import com.manikanta.projects.lovable_backend.repository.UserRepository;
import com.manikanta.projects.lovable_backend.security.AuthUtil;
import com.manikanta.projects.lovable_backend.service.PaymentProcessor;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class StripePaymentProcessor implements PaymentProcessor {
    AuthUtil authUtil;
    PlanRepository planRepository;
    UserRepository userRepository;

    @Value("${client.url}")
    private String frontendUrl;

    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request) {
        // the goal of this particular function is as soon as the user the btn ,
        // we are going to redirect the user to another URL where they can pay via stripe

        Plan plan = planRepository.findById(request.planId()).orElseThrow(() ->
                new ResourceNotFoundException("Plan", request.planId().toString()));

        Long userId = authUtil.getCurrentUserId();
        // Stripe Params builder : refer stripe documentation to impl this

        var params = SessionCreateParams.builder()
                .addLineItem(
                        SessionCreateParams.LineItem.builder().setPrice(plan.getStripePriceId()).setQuantity(1L).build())
                .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                .setSubscriptionData(
                        new SessionCreateParams.SubscriptionData.Builder()
                                .setBillingMode(SessionCreateParams.SubscriptionData.BillingMode.builder()
                                        .setType(SessionCreateParams.SubscriptionData.BillingMode.Type.FLEXIBLE)
                                        .build())
                                .build()
                )
                .setSuccessUrl(frontendUrl + "/success.html?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl(frontendUrl + "/cancel.html")
                .putMetadata("user_id", userId.toString())
                .putMetadata("plan_id", plan.getId().toString());

        try {
            Session session = Session.create(params.build()); // making api call to the Stripe Backend
            return new CheckoutResponse(session.getUrl());
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PortalResponse openCustomerPortal() {
        return null;
    }
}
