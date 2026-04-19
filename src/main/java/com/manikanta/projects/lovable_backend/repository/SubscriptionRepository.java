package com.manikanta.projects.lovable_backend.repository;

import com.manikanta.projects.lovable_backend.entity.Subscription;
import com.manikanta.projects.lovable_backend.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    /*
     * Get the current active subscription
     * */
    Optional<Subscription> findByUserIdAndStatusIn(Long userId, Set<SubscriptionStatus> statusSet);
    boolean existsByStripeSubscriptionId(String subscriptionId);
}
