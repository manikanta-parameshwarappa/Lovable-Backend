package com.manikanta.projects.lovable_backend.entity;


import com.manikanta.projects.lovable_backend.enums.SubscriptionStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Subscription {
    Long id;
    User user;
    Plan plan;
    SubscriptionStatus status;

    String stripeSubscriptionId;
    Instant currentPeriodStart;
    Instant currentPeriodEnd;
    Boolean cancelAtPeriodEnd = false;
    Instant createdAt;
    Instant updatedAt;
}
