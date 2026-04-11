package com.manikanta.projects.lovable_backend.security;

import com.manikanta.projects.lovable_backend.enums.ProjectMemberRole;
import com.manikanta.projects.lovable_backend.enums.ProjectPermission;
import com.manikanta.projects.lovable_backend.repository.ProjectMemberRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component("security")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SecurityExpressions {
    ProjectMemberRepository projectMemberRepository;
    AuthUtil authUtil;

    public boolean canViewProject(Long projectId) {
        Long userId = authUtil.getCurrentUserId();

        return projectMemberRepository.findRoleByProjectIdAndUserId(projectId, userId).
                map(role -> role.getPermissions().contains(ProjectPermission.VIEW))
                .orElse(false);
    }

    public boolean canEditProject(Long projectId) {
        Long userId = authUtil.getCurrentUserId();

        return projectMemberRepository.findRoleByProjectIdAndUserId(projectId, userId).
                map(role -> role.getPermissions().contains(ProjectPermission.EDIT))
                .orElse(false);
    }
}
