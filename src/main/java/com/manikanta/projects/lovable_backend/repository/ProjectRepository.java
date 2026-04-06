package com.manikanta.projects.lovable_backend.repository;

import com.manikanta.projects.lovable_backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("""
            SELECT p from Project p
            WHERE p.owner.id = :userId AND p.deletedAt IS NULL
            ORDER BY p.updatedAt Desc
            """)
    List<Project> findAllAccessibleByUser(@Param("userId") Long userId);


    @Query("""
            SELECT p from Project p
            LEFT JOIN FETCH p.owner
            WHERE p.id = :projectId AND p.deletedAt IS NULL AND p.owner.id = :userId
            """
    )
    Optional<Project> findAccessibleProjectById(@Param("userId")Long userId,
                                                @Param("projectId")Long projectId);

}
