package com.manikanta.projects.lovable_backend.repository;

import com.manikanta.projects.lovable_backend.entity.ProjectMember;
import com.manikanta.projects.lovable_backend.entity.ProjectMemberId;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {
   List<ProjectMember> findByIdProjectId(Long ProjectId);
}
