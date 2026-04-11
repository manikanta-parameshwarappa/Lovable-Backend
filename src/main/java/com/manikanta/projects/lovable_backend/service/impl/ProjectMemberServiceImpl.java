package com.manikanta.projects.lovable_backend.service.impl;

import com.manikanta.projects.lovable_backend.dto.member.InviteMemberRequest;
import com.manikanta.projects.lovable_backend.dto.member.MemeberResponse;
import com.manikanta.projects.lovable_backend.dto.member.UpdateMemberRoleRequest;
import com.manikanta.projects.lovable_backend.entity.Project;
import com.manikanta.projects.lovable_backend.entity.ProjectMember;
import com.manikanta.projects.lovable_backend.entity.ProjectMemberId;
import com.manikanta.projects.lovable_backend.entity.User;
import com.manikanta.projects.lovable_backend.mapper.ProjectMemberMapper;
import com.manikanta.projects.lovable_backend.repository.ProjectMemberRepository;
import com.manikanta.projects.lovable_backend.repository.ProjectRepository;
import com.manikanta.projects.lovable_backend.repository.UserRepository;
import com.manikanta.projects.lovable_backend.security.AuthUtil;
import com.manikanta.projects.lovable_backend.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    UserRepository userRepository;
    AuthUtil authUtil;

    @Override
    public List<MemeberResponse> getProjectMembers(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(userId, projectId);
        return projectMemberRepository.findByIdProjectId(projectId).stream().map(projectMemberMapper::toProjectMemberResponseFromMember).toList();
    }

    @Override
    public MemeberResponse inviteMember(Long projectId, InviteMemberRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(userId, projectId);
        // project owner -  we will have to add using authorization security methods

        User invitee = userRepository.findByUsername(request.username()).orElseThrow();

        if(invitee.getId().equals(userId)){
            throw new RuntimeException("cannot invite yourself");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.getId());

        if(projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("cannot invite once again");
        }

        ProjectMember member = ProjectMember.builder().id(projectMemberId).project(project).user(invitee).role(request.role()).invitedAt(Instant.now()).build();
        projectMemberRepository.save(member);
        return projectMemberMapper.toProjectMemberResponseFromMember(member);
    }

    @Override
    public MemeberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(userId, projectId);
        // project owner -  we will have to add using authorization security methods

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();
        projectMember.setRole(request.role());
        projectMemberRepository.save(projectMember);
        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }


    @Override
    public void removeProjectMember(Long projectId, Long memberId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(userId, projectId);
        // project owner -  we will have to add using authorization security methods
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        if(!projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("does not exists");
        }
        projectMemberRepository.deleteById(projectMemberId);
    }

    // Internal Functions
    public Project getAccessibleProjectById(Long userId, Long projectId) {
        return projectRepository.findAccessibleProjectById(userId, projectId).orElseThrow();
    }
}
