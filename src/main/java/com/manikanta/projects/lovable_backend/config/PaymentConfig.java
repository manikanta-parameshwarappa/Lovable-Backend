package com.manikanta.projects.lovable_backend.config;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {

    @Value("${stripe.api.secret}")
    private String stripeSecretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey; // this will initialize the stripe SDk for us, and this helps in connecting to the stripe dashboard
    }
}
