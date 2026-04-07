package com.manikanta.projects.lovable_backend.controller;


import com.manikanta.projects.lovable_backend.dto.member.InviteMemberRequest;
import com.manikanta.projects.lovable_backend.dto.member.MemeberResponse;
import com.manikanta.projects.lovable_backend.dto.member.UpdateMemberRoleRequest;
import com.manikanta.projects.lovable_backend.service.ProjectMemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/members")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProjectMemberController {
    ProjectMemberService projectMemberService;

    @GetMapping
    public ResponseEntity<List<MemeberResponse>> getProjectMembers(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId, 1L));
    }

    @PostMapping
    public ResponseEntity<MemeberResponse> inviteMember(
            @PathVariable Long projectId,
            @RequestBody InviteMemberRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectMemberService.inviteMember(projectId, request,1L));
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemeberResponse> updateMemberRole(
            @PathVariable Long projectId,
            @PathVariable Long memberId,
            @RequestBody UpdateMemberRoleRequest request
    ){
    return ResponseEntity.ok(projectMemberService.updateMemberRole(projectId,memberId,request,1L));
    }

    @DeleteMapping("{memberId}")
    public ResponseEntity<Void> removeProjectMember(
            @PathVariable Long projectId,
            @PathVariable Long memberId
    ){
        projectMemberService.removeProjectMember(projectId,memberId,1L);
        return ResponseEntity.noContent().build();
    }

}
