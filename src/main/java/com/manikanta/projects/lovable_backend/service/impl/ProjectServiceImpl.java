package com.manikanta.projects.lovable_backend.service.impl;

import com.manikanta.projects.lovable_backend.dto.project.ProjectRequest;
import com.manikanta.projects.lovable_backend.dto.project.ProjectResponse;
import com.manikanta.projects.lovable_backend.dto.project.ProjectSummaryResponse;
import com.manikanta.projects.lovable_backend.entity.Project;
import com.manikanta.projects.lovable_backend.entity.ProjectMember;
import com.manikanta.projects.lovable_backend.entity.ProjectMemberId;
import com.manikanta.projects.lovable_backend.entity.User;
import com.manikanta.projects.lovable_backend.enums.ProjectMemberRole;
import com.manikanta.projects.lovable_backend.error.ResourceNotFoundException;
import com.manikanta.projects.lovable_backend.mapper.ProjectMapper;
import com.manikanta.projects.lovable_backend.repository.ProjectMemberRepository;
import com.manikanta.projects.lovable_backend.repository.ProjectRepository;
import com.manikanta.projects.lovable_backend.repository.UserRepository;
import com.manikanta.projects.lovable_backend.security.AuthUtil;
import com.manikanta.projects.lovable_backend.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional // checks and commit the dirtied fields , so no need to explicitly save , but its good practise to save explicitly
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;
    ProjectMemberRepository projectMemberRepository;
    AuthUtil authUtil;

    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        Long userId = authUtil.getCurrentUserId();
        // User owner = userRepository.findById(userId).orElseThrow(); // this makes a db call to avoid use refrencing


        // it creates a hibernate proxy object , works in @transactional context
        User owner = userRepository.getReferenceById(userId); // makes db call if we need any other further details only


        Project project = Project.builder().name(request.name()).isPublic(false).build(); // why its not default ?
        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(), owner.getId());
        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .role(ProjectMemberRole.OWNER)
                .user(owner)
                .acceptedAt(Instant.now())
                .invitedAt(Instant.now())
                .project(project)
                .build();

        project = projectRepository.save(project);
        projectMemberRepository.save(projectMember);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects() {
        Long userId = authUtil.getCurrentUserId();
        List<Project> projects = projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toListOfProjectSummaryResponse(projects);
    }

    @Override
    public ProjectResponse getUserProjectById(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(userId, projectId);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long projectId, ProjectRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(userId, projectId);
        // project owner -  we will have to add using authorization security methods

        project.setName(request.name());
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDeleteProject(Long projectId) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(userId, projectId);
        // project owner -  we will have to add using authorization security methods
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    // Internal Functions
    public Project getAccessibleProjectById(Long userId, Long projectId){
        return projectRepository.findAccessibleProjectById(userId, projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", projectId.toString()));
    }
}
