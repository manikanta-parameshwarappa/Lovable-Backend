package com.manikanta.projects.lovable_backend.controller;

import com.manikanta.projects.lovable_backend.dto.subscription.*;
import com.manikanta.projects.lovable_backend.entity.Subscription;
import com.manikanta.projects.lovable_backend.service.PaymentProcessor;
import com.manikanta.projects.lovable_backend.service.PlanService;
import com.manikanta.projects.lovable_backend.service.SubscriptionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BillingController {
  PlanService planService;
  SubscriptionService subscriptionService;
  PaymentProcessor paymentProcessor;

  @GetMapping("/api/plans")
  public ResponseEntity<List<PlanResponse>> getAllPlans(){
    return ResponseEntity.ok(planService.getAllactivPlans());
  }


  @GetMapping("/api/me/subscriptions")
  public ResponseEntity<SubscriptionResponse> getMySubscription(){
      return ResponseEntity.ok(subscriptionService.getCurrentSubscription());
  }

  @PostMapping("/api/payments/checkout")
  public ResponseEntity<CheckoutResponse> createCustomResponse(
          @RequestBody CheckoutRequest request
  ){
        return ResponseEntity.ok(paymentProcessor.createCheckoutSessionUrl(request));
  }


  @PostMapping("/api/payments/portal")
  public ResponseEntity<PortalResponse> openCustomPortal(){
      return ResponseEntity.ok(paymentProcessor.openCustomerPortal());
  }

}
