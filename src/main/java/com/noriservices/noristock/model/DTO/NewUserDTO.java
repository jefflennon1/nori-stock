package com.noriservices.noristock.model.DTO;

import com.noriservices.noristock.model.UserRole;

public record NewUserDTO(String username, String name, String email, String password, UserRole role) {
}
