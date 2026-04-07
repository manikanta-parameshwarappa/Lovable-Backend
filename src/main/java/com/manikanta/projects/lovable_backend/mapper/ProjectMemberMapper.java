package com.manikanta.projects.lovable_backend.mapper;

import com.manikanta.projects.lovable_backend.dto.member.MemeberResponse;
import com.manikanta.projects.lovable_backend.entity.ProjectMember;
import com.manikanta.projects.lovable_backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {
    @Mapping(target = "userId", source = "id")
    @Mapping(target = "projectMemberRole", constant = "OWNER")
    MemeberResponse toMemberResponseFromOwner(User owner);

    //nested dto mapping
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "name", source = "user.name")
    MemeberResponse toProjectMemberResponseFromMember(ProjectMember projectMember);
}
