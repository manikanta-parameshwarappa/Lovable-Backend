package com.manikanta.projects.lovable_backend.repository;

import com.manikanta.projects.lovable_backend.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
}