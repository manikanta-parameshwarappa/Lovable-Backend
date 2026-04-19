package com.manikanta.projects.lovable_backend.service;
import com.manikanta.projects.lovable_backend.dto.subscription.SubscriptionResponse;
import com.manikanta.projects.lovable_backend.enums.SubscriptionStatus;
import java.time.Instant;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription();
    void activateSubscription(Long userId, Long planId, String subscriptionId, String customerId);
    void updateSubscription(String id, SubscriptionStatus status, Instant periodStart, Instant periodEnd, Boolean cancelAtPeriodEnd, Long planId);
    void cancelSubscription(String id);
    void renewSubscriptionPeriod(String subId, Instant periodStart, Instant periodEnd);
}
