package com.manikanta.projects.lovable_backend.service;

import com.manikanta.projects.lovable_backend.dto.member.InviteMemberRequest;
import com.manikanta.projects.lovable_backend.dto.member.MemeberResponse;
import com.manikanta.projects.lovable_backend.dto.member.UpdateMemberRoleRequest;

import java.util.List;

public interface ProjectMemberService {
    MemeberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request);
    List<MemeberResponse> getProjectMembers(Long projectId);
    MemeberResponse inviteMember(Long projectId, InviteMemberRequest request);
    void removeProjectMember(Long projectId, Long memberId);
}
