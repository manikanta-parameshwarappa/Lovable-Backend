package com.manikanta.projects.lovable_backend.service.impl;

import com.manikanta.projects.lovable_backend.dto.subscription.CheckoutRequest;
import com.manikanta.projects.lovable_backend.dto.subscription.CheckoutResponse;
import com.manikanta.projects.lovable_backend.dto.subscription.PortalResponse;
import com.manikanta.projects.lovable_backend.dto.subscription.SubscriptionResponse;
import com.manikanta.projects.lovable_backend.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Override
    public SubscriptionResponse getCurrentSubscription() {
        return null;
    }

    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request) {
        return null;
    }

    @Override
    public PortalResponse openCustomPortal() {
        return null;
    }
}
