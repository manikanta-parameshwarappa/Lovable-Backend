package com.manikanta.projects.lovable_backend.dto.member;

import com.manikanta.projects.lovable_backend.enums.ProjectMemberRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InviteMemberRequest(
    @Email @NotBlank String username,
    @NotNull ProjectMemberRole role
) {
}
