package com.manikanta.projects.lovable_backend.entity;

import com.manikanta.projects.lovable_backend.enums.ProjectMemberRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "project_members")
public class ProjectMember {
    @EmbeddedId
    ProjectMemberId id;

    @ManyToOne
    @MapsId("projectId")
    Project project; // we don't need joins table so ignore many to many

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    ProjectMemberRole role;

    @ManyToOne
    @MapsId("userId")
    User user;
    Instant invitedAt;

    Instant acceptedAt;
}
