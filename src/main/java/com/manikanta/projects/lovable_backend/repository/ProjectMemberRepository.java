package com.manikanta.projects.lovable_backend.repository;

import com.manikanta.projects.lovable_backend.entity.ProjectMember;
import com.manikanta.projects.lovable_backend.entity.ProjectMemberId;
import com.manikanta.projects.lovable_backend.enums.ProjectMemberRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {
   List<ProjectMember> findByIdProjectId(Long ProjectId);

    @Query("""
            SELECT pm.role FROM ProjectMember pm
            WHERE pm.id.projectId = :projectId AND pm.id.userId = :userId
            """)
    Optional<ProjectMemberRole> findRoleByProjectIdAndUserId(@Param("projectId") Long projectId,
                                                             @Param("userId") Long userId);

    // projection : fetch only what you need , example :role

    @Query("""
            SELECT COUNT(pm) FROM ProjectMember pm
            WHERE pm.id.userId = :userId AND pm.role = "OWNER"
            """)
    int countProjectOwnedByUser(@Param("userId") Long userId);
}
