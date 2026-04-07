package com.manikanta.projects.lovable_backend.dto.auth;

public record UserProfileResponse(
    Long id,
    String name,
    String username
){
}
