package com.manikanta.projects.lovable_backend.service.impl;
import com.manikanta.projects.lovable_backend.dto.subscription.SubscriptionResponse;
import com.manikanta.projects.lovable_backend.entity.Subscription;
import com.manikanta.projects.lovable_backend.enums.SubscriptionStatus;
import com.manikanta.projects.lovable_backend.mapper.SubscriptionMapper;
import com.manikanta.projects.lovable_backend.repository.SubscriptionRepository;
import com.manikanta.projects.lovable_backend.security.AuthUtil;
import com.manikanta.projects.lovable_backend.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final AuthUtil authUtil;
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    @Override
    public SubscriptionResponse getCurrentSubscription() {
        Long userId = authUtil.getCurrentUserId();

        var currentSubscription = subscriptionRepository.findByUserIdAndStatusIn(userId, Set.of(
                SubscriptionStatus.ACTIVE, SubscriptionStatus.PAST_DUE,
                SubscriptionStatus.TRIALING
        )).orElse(
                new Subscription()
        );

        return subscriptionMapper.toSubscriptionResponse(currentSubscription);
    }

    @Override
    public void activateSubscription(Long userId, Long planId, String subscriptionId, String customerId) {
    }

    @Override
    public void updateSubscription(String gatewaySubscriptionId, SubscriptionStatus status, Instant periodStart, Instant periodEnd, Boolean cancelAtPeriodEnd, Long planId) {
    }

    @Override
    public void cancelSubscription(String gatewaySubscriptionId) {
    }

    @Override
    public void renewSubscriptionPeriod(String subId, Instant periodStart, Instant periodEnd) {
    }

    @Override
    public void markSubscriptionPastDue(String subId) {
    }
}
