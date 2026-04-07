package com.manikanta.projects.lovable_backend.dto.member;

import com.manikanta.projects.lovable_backend.enums.ProjectMemberRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(
        @NotNull ProjectMemberRole role
) {
}
