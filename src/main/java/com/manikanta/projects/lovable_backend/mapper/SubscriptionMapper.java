package com.manikanta.projects.lovable_backend.mapper;

import com.manikanta.projects.lovable_backend.dto.subscription.PlanResponse;
import com.manikanta.projects.lovable_backend.dto.subscription.SubscriptionResponse;
import com.manikanta.projects.lovable_backend.entity.Plan;
import com.manikanta.projects.lovable_backend.entity.Subscription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    SubscriptionResponse toSubscriptionResponse(Subscription subscription);
    PlanResponse toPlanResponse(Plan plan);
}
