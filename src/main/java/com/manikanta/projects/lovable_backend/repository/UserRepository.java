package com.manikanta.projects.lovable_backend.repository;

import com.manikanta.projects.lovable_backend.entity.Project;
import com.manikanta.projects.lovable_backend.entity.User;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
