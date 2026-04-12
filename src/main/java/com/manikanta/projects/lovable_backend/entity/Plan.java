package com.manikanta.projects.lovable_backend.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Column(unique = true)
    String stripePriceId;

    Integer maxProjects;
    Integer maxTokensPerDay;
    Integer maxPreviews; //max number of previews allowed per plan
    Boolean unlimitedTokens; //unlimited access to LLM, ignore maxTokensPerDay if true

    Boolean active;
}


// data

// record: 1
// id: 1
// name: Pro Plan
// stripePriceId: price_1TLQiPFJIiYbqsX7KUYPI7tf ( get this from the stripe dashboard after creating this particular product)
// maxProjects: 3
// maxTokensPerDay: 10000
// maxPreviews: 1
// unlimitedTokens: true
// active: true

// record: 2
// id: 2
// name: Business Plan
// stripePriceId: price_1TLQjkFJIiYbqsX7qQHnr9lK
// maxProjects: 10
// maxTokensPerDay: 50000
// maxPreviews: 3
// unlimitedTokens: true
// active: true