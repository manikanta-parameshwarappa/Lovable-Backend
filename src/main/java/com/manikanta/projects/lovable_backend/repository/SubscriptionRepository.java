package com.manikanta.projects.lovable_backend.repository;

import com.manikanta.projects.lovable_backend.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
