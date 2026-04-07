package com.manikanta.projects.lovable_backend.service;

import com.manikanta.projects.lovable_backend.dto.member.InviteMemberRequest;
import com.manikanta.projects.lovable_backend.dto.member.MemeberResponse;
import com.manikanta.projects.lovable_backend.dto.member.UpdateMemberRoleRequest;

import java.util.List;

public interface ProjectMemberService {
    MemeberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId);
    List<MemeberResponse> getProjectMembers(Long projectId, Long userId);
    MemeberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId);
    void removeProjectMember(Long projectId, Long memberId, Long userId);
}
