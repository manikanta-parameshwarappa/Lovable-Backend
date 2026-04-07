package com.manikanta.projects.lovable_backend.dto.member;

import com.manikanta.projects.lovable_backend.enums.ProjectMemberRole;

import java.time.Instant;

public record MemeberResponse(
        Long userId,
        String email,
        String name,
        ProjectMemberRole projectMemberRole,
        Instant invitedAt
) {
}
