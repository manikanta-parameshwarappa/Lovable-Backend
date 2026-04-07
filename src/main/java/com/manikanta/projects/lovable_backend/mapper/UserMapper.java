package com.manikanta.projects.lovable_backend.mapper;

import com.manikanta.projects.lovable_backend.dto.auth.SignupRequest;
import com.manikanta.projects.lovable_backend.dto.auth.UserProfileResponse;
import com.manikanta.projects.lovable_backend.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(SignupRequest signupRequest);
    UserProfileResponse toUserProfileResponse(User user);
}
