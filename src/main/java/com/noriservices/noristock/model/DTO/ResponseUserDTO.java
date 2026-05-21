package com.noriservices.noristock.model.DTO;

import com.noriservices.noristock.model.UserRole;

import java.time.LocalDateTime;

public record ResponseUserDTO(String username, String name, String email, UserRole role, boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
