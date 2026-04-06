package com.manikanta.projects.lovable_backend.service.impl;

import com.manikanta.projects.lovable_backend.dto.project.ProjectRequest;
import com.manikanta.projects.lovable_backend.dto.project.ProjectResponse;
import com.manikanta.projects.lovable_backend.dto.project.ProjectSummaryResponse;
import com.manikanta.projects.lovable_backend.entity.Project;
import com.manikanta.projects.lovable_backend.entity.User;
import com.manikanta.projects.lovable_backend.mapper.ProjectMapper;
import com.manikanta.projects.lovable_backend.repository.ProjectRepository;
import com.manikanta.projects.lovable_backend.repository.UserRepository;
import com.manikanta.projects.lovable_backend.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow();
        Project project = Project.builder().name(request.name()).owner(owner).isPublic(false).build(); // why its not default ?
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {
        List<Project> projects = projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toListOfProjectSummaryResponse(projects);
    }

    @Override
    public ProjectResponse getUserProjectById(Long userId, Long projectId) {
        Project project = getAccessibleProjectById(userId, projectId);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long projectId, ProjectRequest request, Long userId) {
        Project project = getAccessibleProjectById(userId, projectId);
        project.setName(request.name());
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDeleteProject(Long projectId, Long userId) {
        Project project = getAccessibleProjectById(userId, projectId);
        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("you are not allowed to delete");
        }

        project.setDeletedAt(Instant.now());
        project = projectRepository.save(project);
    }

    // Internal Functions
    public Project getAccessibleProjectById(Long userId, Long projectId){
        return projectRepository.findAccessibleProjectById(userId, projectId).orElseThrow();
    }
}
