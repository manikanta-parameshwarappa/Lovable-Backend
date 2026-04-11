package com.manikanta.projects.lovable_backend.enums;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.manikanta.projects.lovable_backend.enums.ProjectPermission.*;

import java.util.Set;

@RequiredArgsConstructor
@Getter
public enum ProjectMemberRole {
    EDITOR(VIEW, EDIT, DELETE, VIEW_MEMBERS),
    VIEWER(Set.of(VIEW, VIEW_MEMBERS)),
    OWNER(Set.of(VIEW, EDIT, DELETE, MANAGE_MEMBERS, VIEW_MEMBERS));

    ProjectMemberRole(ProjectPermission... permissions) {
        this.permissions = Set.of(permissions);
    }

    private final Set<ProjectPermission> permissions;
}
